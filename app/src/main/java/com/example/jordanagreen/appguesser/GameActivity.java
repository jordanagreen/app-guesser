package com.example.jordanagreen.appguesser;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    public static final String TAG = "GameActivity";
    private static final int APPS_TO_SHOW = 5;

    private ArrayAdapter<App> adapter;
    private ArrayList<App> allApps;
    private ArrayList<App> currentApps;
    private ListView appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        allApps = getInstalledApps();
        Log.d(TAG, "Got installed apps");
        currentApps = getRandomApps(allApps, APPS_TO_SHOW);
        adapter = new AppAdapter(this, currentApps);
        appList = (ListView) findViewById(R.id.appList);
        appList.setAdapter(adapter);
    }

    private ArrayList<App> getInstalledApps(){
        ArrayList<App> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();
        for (PackageInfo packageInfo: pm.getInstalledPackages(PackageManager.GET_META_DATA)){
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            if (appInfo == null) continue;
            String name = pm.getApplicationLabel(appInfo).toString();
            long dateInstalled = packageInfo.firstInstallTime;
            Log.d(TAG, name + " " + dateInstalled);
            apps.add(new App(name, dateInstalled));
        }
        return apps;
    }

    private ArrayList<App> getRandomApps(ArrayList<App> apps, int count){
        ArrayList<App> copy = new ArrayList<>(apps);
        Collections.shuffle(copy);
        if (count >= copy.size()) return copy;
        return new ArrayList<>(copy.subList(0, count));
    }

}
