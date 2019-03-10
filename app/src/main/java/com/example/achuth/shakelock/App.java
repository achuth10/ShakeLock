package com.example.achuth.shakelock;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import static android.os.Build.VERSION.SDK_INT;

public class App extends Application {
    public static final String CHANNEL_ID = "LockServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannnel();
    }

    private void createNotificationChannnel()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel  = new NotificationChannel(
                    CHANNEL_ID,"Lock Service Channel ",NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager =getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
        }
    }


}
