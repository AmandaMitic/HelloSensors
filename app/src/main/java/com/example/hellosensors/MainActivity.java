package com.example.hellosensors;

import androidx.appcompat.app.AppCompatActivity;
//Tillagt
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
//Tillagt
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void compassPage(View view){
        //Call the AccelerometerActivity class
        Intent intent = new Intent(this, CompassActivity.class);
        this.startActivity ( intent );
    }

    public void accelerometerPage(View view){
        //Call the CompassActivity class
        Intent intent = new Intent(this, AccelerometerActivity.class);
        this.startActivity ( intent );
    }
}