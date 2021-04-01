package com.example.hellosensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xValue, yValue, zValue;
    private SensorManager sensormanager;
    private Sensor accelerometer;
    private TextView message;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Get the object from the page
        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);
        message = (TextView) findViewById(R.id.message);

        //Tillagt
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Listener
        sensormanager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       // Log.d(TAG, "onSensorChanged: X: " + event.values[0] + "Y: " + event.values[1] + "Z: " + event.values[2]);

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            //Round to 3 decimals for x, y and z
            String xNew = String.format("X: %.3f", x);
            String yNew = String.format("Y: %.3f", y);
            String zNew = String.format("Z: %.3f", z);

            xValue.setText(xNew);
            yValue.setText(yNew);
            zValue.setText(zNew);

            if (x < -1){
                message.setText("Your phone is tilting right!");
                if (Build.VERSION.SDK_INT >= 26 && x < -8) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else if((Build.VERSION.SDK_INT < 26 && x < 8)){
                    //deprecated in API 26
                    vibrator.vibrate(500);
                }
            } else if(x >1) {
                message.setText("Your phone is tilting left!");
                if (Build.VERSION.SDK_INT >= 26 && x > 8) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else if((Build.VERSION.SDK_INT < 26 && x > 8)){
                    //deprecated in API 26
                    vibrator.vibrate(500);
                }
            } else {
                message.setText("Your phone is facing upwards/downwards!");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }
}