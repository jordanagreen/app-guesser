package com.example.jordanagreen.appguesser;

/**
 * Created by Jordan on 6/20/2016.
 */
public class App {

    private String name;
    private long dateInstalled;

    App(String name, long dateInstalled){
        this.name = name;
        this.dateInstalled = dateInstalled;
    }

    String getName(){
        return name;
    }

    long getDateInstalled(){
        return dateInstalled;
    }
}
