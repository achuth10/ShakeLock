package com.example.achuth.shakelock;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import static com.example.achuth.shakelock.App.CHANNEL_ID;

public class LockService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //Intent notificationIntent = new Intent(this,MainActivity.class);
    //PendingIntent pendingIntent = PendingIntent.getActivity(this,
           // 0,notificationIntent,0);

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification= new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Lock Service")
                .setContentText("ShakeLock is running")
                .setSmallIcon(R.drawable.ic_launcher_background)
                //.setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

    return START_REDELIVER_INTENT;
    }
}
