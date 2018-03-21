package com.example.mithun.notificationservice;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

public class NotificationListener extends NotificationListenerService{
    //public static String TAG = "NotificationListenerTesting";
    //private StatusBarNotification[] mStatusBarNotification;
    Context context;
    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        Log.v("NotificationTesting", "Inside on create");
    }
    /*@Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }*/
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.v("NotificationTesting", "Inside onNotificationPosted");
        //TAG = "onNotificationPosted";
        Log.v("onNotificationPosted", "Package Name : " + sbn.getPackageName() +
                "Title : " + sbn.getNotification().extras.getString("android.title"));
        Toast.makeText(context,sbn.getNotification().extras.getString("android.title"),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        //TAG = "onNotificationRemoved";
        Log.v("onNotificationRemoved", "id = " + sbn.getId() + "Package Name" + sbn.getPackageName() +
                "Post time = " + sbn.getPostTime() + "Tag = " + sbn.getTag());

    }
}
