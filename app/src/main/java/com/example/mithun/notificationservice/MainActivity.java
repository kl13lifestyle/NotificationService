package com.example.mithun.notificationservice;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final int REQUEST_READ_PHONE_STATE=100;
    private static final int REQUEST_READ_SMS_PERMISSION=101;
    public ArrayList<appObject> applications = new ArrayList<appObject>();


    public void getPhoneStatePermission(View view) {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }
    }

    public void getSMSPermission(View view) {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED) {
            Log.v("inside getSMSPermission","inside get");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS}, REQUEST_READ_SMS_PERMISSION);
            Log.v("getSMSPermissonLate",String.valueOf(REQUEST_READ_SMS_PERMISSION));

        }
    }

    public void getNotificationPermission(View view) {
        Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Great. You can get call notifications",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"Sorry you cant get call notifications",Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case REQUEST_READ_SMS_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Great. You can get sms notifications",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"Sorry you cant get sms notifications",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    public void getApplicationList(View view){
        final PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);

       LayoutInflater linf=(LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        linf = LayoutInflater.from(this);
        LinearLayout rr =  findViewById(R.id.appList);


        for (ResolveInfo ri: apps) {
            Log.d("Installed App", "APP DETAILS:" + ri.activityInfo.applicationInfo.loadLabel(pm).toString() + " -> " +
                    ri.activityInfo.applicationInfo.packageName);

            appObject app=new appObject();
            app.appName=ri.activityInfo.applicationInfo.loadLabel(pm).toString();
            app.packageName=ri.activityInfo.applicationInfo.packageName;
            app.icon=ri.activityInfo.loadIcon(pm);
            applications.add(app);

            ImageView icon = findViewById(R.id.appIcon);
            icon.setImageDrawable(ri.activityInfo.loadIcon(pm));
            final View v = linf.inflate(R.layout.app_list_item, null);

            rr.addView(v);
        }


    }

    public class appObject{
        public String appName;
        public String packageName;
        public Drawable icon;
    }
}
