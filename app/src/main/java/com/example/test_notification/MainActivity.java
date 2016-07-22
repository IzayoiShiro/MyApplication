package com.example.test_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void build(View view) {
        Intent intent = new Intent(this, MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("");
//        Notification notify = builder.build();
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("loading.....")
                .setContentInfo("Hello World")
                .setTicker("Hello World!!!")
                .setContentText("您有新的消息，请注意查收")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();
        manager.notify(1, notification);
    }

    public void foreground(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stopforeground(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void startbroadcast(View view) {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("loading.....")
                .setTicker("Hello World!!!")
                .setContentText("您有新的消息，请注意查收")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();
        manager.notify(3, notification);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void custom(View view) {
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.view);
        contentView.setImageViewResource(R.id.image
                , android.R.drawable.sym_def_app_icon);
        contentView.setTextViewText(R.id.text
                , "Hello,this message is in a custom expanded view");
        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
//                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setTicker("Hello World!!!")
//                .setWhen(System.currentTimeMillis())
                .setContent(contentView)
                .setContentIntent(pendingIntent)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(4, notification);

    }
}
