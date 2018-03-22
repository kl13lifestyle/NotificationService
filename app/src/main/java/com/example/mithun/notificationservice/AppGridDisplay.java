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

import com.example.mithun.notificationservice.R;

import java.util.ArrayList;
import java.util.List;



public class AppGridDisplay extends AppCompatActivity {

    GridView grid;
    public ArrayList<appObject> applications = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_list);
        applications = getApplicationList();

        ImageAdapter adapter = new ImageAdapter(this,applications);

        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.v("onItemClick","Clicked on : " + applications.get(position).packageName);
                Toast.makeText(AppGridDisplay.this,"clicked : " + applications.get(position).packageName, Toast.LENGTH_LONG).show();

            }
        });
    }

    public ArrayList<appObject> getApplicationList(){
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
        }
        return applications;
    }

    public class appObject{
        public String appName;
        public String packageName;
        public Drawable icon;
    }
}
