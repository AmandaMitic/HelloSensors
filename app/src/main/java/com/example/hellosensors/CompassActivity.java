package com.example.hellosensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensormanager;
    private Sensor vector, accelerometerSensor, magnetometerSensor;
    private TextView value;
    private ImageView rose;
    private float[] rotation;
    private float[] rotationResult;
    private int currentDegree;
    private int azimuth = 0;
    private float[] accelerometer = new float[3];
    private float[] magnetometer = new float[3];
    private boolean accelerometerBool;
    private boolean magnetometerBool;
    private float LOWPASS_FACTOR = 0.25f;
    Vibrator vibrator;
    ConstraintLayout color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        color = findViewById(R.id.background);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        magnetometerSensor = sensormanager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometerSensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vector = sensormanager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        sensormanager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        sensormanager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_UI);
        sensormanager.registerListener(this, vector, SensorManager.SENSOR_DELAY_UI);

        rotation = new float[9];
        rotationResult = new float[3];

        value = (TextView) findViewById(R.id.degree);
        rose = (ImageView) findViewById(R.id.rose);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotation, event.values);
            azimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rotation, rotationResult)[0]) + 360) % 360;
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(lowPass(event.values, accelerometer), 0, accelerometer, 0, event.values.length);

             //System.arraycopy(event.values, 0, accelerometer, 0, event.values.length);
            accelerometerBool = true;
        }
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(lowPass(event.values, magnetometer), 0, magnetometer, 0, event.values.length);

            //System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            magnetometerBool = true;
        }
        if (accelerometerBool && magnetometerBool) {
            SensorManager.getRotationMatrix(rotation, null, accelerometer, magnetometer);
            SensorManager.getOrientation(rotation, rotationResult);
            azimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rotation, rotationResult)[0]) + 360) % 360;
        }

        if (azimuth >= 345 || azimuth <= 15) {
            value.setText(azimuth + "° NORTH");
            if (Build.VERSION.SDK_INT >=26) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                vibrator.vibrate(500);
            }
        }
        else if (azimuth < 345 && azimuth >= 325) {
          value.setText(azimuth + "° NORTHWEST");
        }
        else if (azimuth < 325 && azimuth >= 255) {
           value.setText(azimuth + "° WEST");
        }
        else if (azimuth < 255 && azimuth >= 185) {
            value.setText(azimuth + "° SOUTHWEST");
        }
        else if (azimuth < 185 && azimuth >= 165) {
            value.setText(azimuth + "° SOUTH");
        }
        else if (azimuth < 165 && azimuth >= 95) {
            value.setText(azimuth + "° SOUTHEAST");
        }
        else if (azimuth < 95 && azimuth >= 75) {
            value.setText(azimuth + "° EAST");
        }
        else {
            value.setText(azimuth + "° NORTHEAST");
        }

        //Change the color to red when the compass points towards North otherwise white
        if (azimuth >= 345 || azimuth <= 15) {
            color.setBackgroundColor(Color.RED);
        } else if(azimuth <= 345 || azimuth >= 15){
            color.setBackgroundColor(Color.WHITE);
        }
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        // set the compass animation after the end of the reservation status
        ra.setFillAfter(true);

        // set how long the animation for the compass image will take place
        ra.setDuration(210);

        // Start animation of compass image
        rose.startAnimation(ra);
        currentDegree = -azimuth;

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TOM
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, vector, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    protected float[] lowPass (float[] input, float[] output) {
        if (output == null)
            return input;
        for (int i = 0; i < input.length; i++) {
            output[i] += LOWPASS_FACTOR * (input[i] - output[i]);
        }
        return output;
    }
}