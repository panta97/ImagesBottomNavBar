package com.example.roosevelt.imagesbottomnavbar;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class ImagesBottomNavBarApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
