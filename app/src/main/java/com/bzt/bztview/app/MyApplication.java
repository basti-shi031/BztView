package com.bzt.bztview.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by SHIBW-PC on 2015/12/3.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
