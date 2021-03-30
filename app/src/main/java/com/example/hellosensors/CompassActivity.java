package com.example.hellosensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensormanager;
    private Sensor vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vector = sensormanager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //KOD
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
}