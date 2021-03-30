package com.example.hellosensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xValue, yValue, zValue;
    private SensorManager sensormanager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        //Get the object from the page
        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

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

            String xNew = ("X: " + x);
            String yNew = ("Y: " + y);
            String zNew = ("Z: " + z);


            xValue.setText(xNew);
            yValue.setText(yNew);
            zValue.setText(zNew);
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