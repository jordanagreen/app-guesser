package com.example.jordanagreen.appguesser;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements OnItemClickListener {

    public static final String TAG = "GameActivity";
    private static final int APPS_TO_SHOW = 5;

    private ArrayAdapter<App> adapter;
    private ArrayList<App> allApps;
    private ArrayList<App> currentApps;
    private ListView appList;
    private int earliestIndex;
    private int currentStreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        allApps = getInstalledApps();
        currentApps = new ArrayList<>();
        Log.d(TAG, "Got installed apps");
        adapter = new AppAdapter(this, currentApps);
        appList = (ListView) findViewById(R.id.appList);
        appList.setAdapter(adapter);
        appList.setOnItemClickListener(this);
        refreshAppList();
    }

    private ArrayList<App> getInstalledApps(){
        ArrayList<App> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();
        for (PackageInfo packageInfo: pm.getInstalledPackages(PackageManager.GET_META_DATA)){
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            if (appInfo == null) continue;
            String name = pm.getApplicationLabel(appInfo).toString();
            long dateInstalled = packageInfo.firstInstallTime;
            Drawable icon = pm.getApplicationIcon(appInfo);
            Log.d(TAG, name + " " + dateInstalled);
            apps.add(new App(name, dateInstalled, icon));
        }
        return apps;
    }

    private ArrayList<App> getRandomApps(ArrayList<App> apps, int count){
        ArrayList<App> copy = new ArrayList<>(apps);
        Collections.shuffle(copy);
        if (count >= copy.size()) return copy;
        return new ArrayList<>(copy.subList(0, count));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "Item " + i + ": " + appList.getAdapter().getItem(i) + " clicked");
        if (i == earliestIndex){
            currentStreak++;
            Toast.makeText(GameActivity.this, "Correct: Score = " + currentStreak,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GameActivity.this, "Wrong: Correct answer was " +
                    currentApps.get(earliestIndex), Toast.LENGTH_SHORT).show();
            currentStreak = 0;
        }
        refreshAppList();
    }

    private void refreshAppList(){
        Log.d(TAG, "Refreshing app list");
        currentApps.clear();
        currentApps.addAll(getRandomApps(allApps, APPS_TO_SHOW));
        long earliest = Long.MAX_VALUE;
        for (int i = 0; i < currentApps.size(); i++){
            if (currentApps.get(i).getDateInstalled() < earliest){
                earliestIndex = i;
            }
        }
        adapter.notifyDataSetChanged();
        Log.d(TAG, "Earliest app is " + earliestIndex + ": " + currentApps.get(earliestIndex));
    }
}
