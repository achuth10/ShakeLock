package com.example.achuth.shakelock;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    private Button button,start,stop;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.reboot);
        start=(Button)findViewById(R.id.startservice);
        stop=(Button)findViewById(R.id.stopservice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Runtime.getRuntime().exec("su -c /system/bin/input keyevent 26");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this,LockService.class);
                //startService(serviceIntent);
                ContextCompat.startForegroundService(MainActivity.this,serviceIntent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this,LockService.class);
                stopService(serviceIntent);
            }
        });
    }
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccelerometer = mSensorManager
//                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mShakeDetector = new ShakeDetector();
//        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
//
//            public void onShake(int count) {
//                /*
//                 * The following method, "handleShakeEvent(count):" is a stub //
//                 * method you would use to setup whatever you want done once the
//                 * device has been shook.
//                 */
//                try {
//                    Runtime.getRuntime().exec("su -c /system/bin/input keyevent 26");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//    public void onResume() {
//        super.onResume();
//        // Add the following line to register the Session Manager Listener onResume
//        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
//    }
//
//    @Override
//    public void onPause() {
//        // Add the following line to unregister the Sensor Manager onPause
//        mSensorManager.unregisterListener(mShakeDetector);
//        super.onPause();
//    }

}






