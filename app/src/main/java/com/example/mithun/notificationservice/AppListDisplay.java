package com.example.mithun.notificationservice;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AppListDisplay extends AppCompatActivity {

    public LayoutInflater linf;
    public LinearLayout rr;
    private View view;
    public ArrayList<appObject> applications = new ArrayList<appObject>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list);
        ViewGroup parent = (ViewGroup) findViewById(R.id.appList);


        final PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);

        for (ResolveInfo ri: apps) {
            Log.d("Installed App", "APP DETAILS:" + ri.activityInfo.applicationInfo.loadLabel(pm).toString() + " -> " +
                    ri.activityInfo.applicationInfo.packageName);

            appObject app=new appObject();
            app.appName=ri.activityInfo.applicationInfo.loadLabel(pm).toString();
            app.packageName=ri.activityInfo.applicationInfo.packageName;
            app.icon=ri.activityInfo.loadIcon(pm);
            applications.add(app);


            rr =  findViewById(R.id.appList);
            view=linf.from(this).inflate(R.layout.app_list_item,null);

            //view= linf.inflate(R.layout.app_list_item,null);
            ImageView icon = view.findViewById(R.id.appIcon);
            icon.setBackground(ri.activityInfo.loadIcon(pm));
            parent.addView(view);
            /*rr.addView(v);*/
        }

        //linf=getLayoutInflater();

        /*GridView gridview = (GridView) findViewById(R.id.appGridList);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(HelloGridView.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });*/
    }




    /*public void getApplicationList(View view){
        final PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);

        for (ResolveInfo ri: apps) {
            Log.d("Installed App", "APP DETAILS:" + ri.activityInfo.applicationInfo.loadLabel(pm).toString() + " -> " +
                    ri.activityInfo.applicationInfo.packageName);

            appObject app=new appObject();
            app.appName=ri.activityInfo.applicationInfo.loadLabel(pm).toString();
            app.packageName=ri.activityInfo.applicationInfo.packageName;
            app.icon=ri.activityInfo.loadIcon(pm);
            applications.add(app);


            rr =  findViewById(R.id.appList);
            View view= linf.inflate(R.layout.app_list_item,null);
            ImageView icon = v.findViewById(R.id.appIcon);
            icon.setBackground(ri.activityInfo.loadIcon(pm));
            rr.addView(v);
        }
    }*/

    public class appObject{
        public String appName;
        public String packageName;
        public Drawable icon;
    }
}
