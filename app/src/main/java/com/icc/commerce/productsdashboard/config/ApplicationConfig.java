package com.icc.commerce.productsdashboard.config;

import android.app.Application;

import com.icc.commerce.productsdashboard.BuildConfig;
import com.icc.commerce.productsdashboard.di.components.AppComponent;
import com.icc.commerce.productsdashboard.di.components.DaggerAppComponent;
import com.icc.commerce.productsdashboard.di.modules.AppModule;
import com.icc.commerce.productsdashboard.di.modules.NetModule;

import timber.log.Timber;


public class ApplicationConfig extends Application {
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO create a new custom timber tree for the release version of the app
        }
    }
    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }
}
