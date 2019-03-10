package com.example.achuth.shakelock;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.squareup.seismic.ShakeDetector;

import java.io.IOException;

import static com.example.achuth.shakelock.App.CHANNEL_ID;

public class LockService extends Service implements ShakeDetector.Listener{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private ShakeDetector sd;


    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sd = new com.squareup.seismic.ShakeDetector(this);
        sd.start(mSensorManager);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Lock Service")
                .setContentText("ShakeLock is running")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void hearShake() {
        System.out.println("Shook");
        try {
            Runtime.getRuntime().exec("su -c /system/bin/input keyevent 26");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

