package com.example.jordanagreen.appguesser;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    public static final String TAG = "GameActivity";

    private ArrayAdapter<App> adapter;
    private ArrayList<App> allApps;
    private ListView appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        allApps = getInstalledApps();
        Log.d(TAG, "Got installed apps");
        adapter = new AppAdapter(this, allApps);
        appList = (ListView) findViewById(R.id.appList);
        appList.setAdapter(adapter);
    }

    private ArrayList<App> getInstalledApps(){
        ArrayList<App> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();
        for (ApplicationInfo info: pm.getInstalledApplications(PackageManager.GET_META_DATA)){
            String name = pm.getApplicationLabel(info).toString();
            long dateInstalled = getDateInstalled(info);
            Log.d(TAG, name + " " + dateInstalled);
            apps.add(new App(name, dateInstalled));
        }
        return apps;
    }

    private long getDateInstalled(ApplicationInfo info){
        return 0;
    }

}
