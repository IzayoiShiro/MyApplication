package com.example.test_notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

/**
 * Created by 杨天宇 on 2016/7/22.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.sym_def_app_icon);
        builder.setContentTitle("Notification Title");
        builder.setContentText("Notification comes");
        builder.setContentIntent(PendingIntent.getActivity(getApplicationContext(),
                0,
                new Intent(getApplicationContext(), MyActivity.class),
                0));
        Notification notification = builder.build();
        startForeground(2, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
