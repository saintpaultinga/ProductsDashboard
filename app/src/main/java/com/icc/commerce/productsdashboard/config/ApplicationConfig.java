package com.icc.commerce.productsdashboard.config;

import android.app.Application;

import com.icc.commerce.productsdashboard.BuildConfig;

import timber.log.Timber;


public class ApplicationConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO create a new custom timber tree for the release version of the app
        }
    }
}
