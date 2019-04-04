package com.example.misha.myapplication;

import android.app.Application;
import android.content.Context;
//Todo Application и final/static
public class ScheduleApp extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public final static Context getAppContext() {
        return appContext;
    }


}

