package com.example.jordanagreen.appguesser;

import android.graphics.drawable.Drawable;

/**
 * Created by Jordan on 6/20/2016.
 */
public class App {

    private String name;
    private long dateInstalled;
    private Drawable icon;

    App(String name, long dateInstalled, Drawable icon){
        this.name = name;
        this.dateInstalled = dateInstalled;
        this.icon = icon;
    }

    String getName(){
        return name;
    }

    long getDateInstalled(){
        return dateInstalled;
    }

    Drawable getIcon(){
        return icon;
    }
}
