package com.icc.commerce.productsdashboard.di.components;

import com.icc.commerce.productsdashboard.ui.DashboardActivity;
import com.icc.commerce.productsdashboard.di.modules.AppModule;
import com.icc.commerce.productsdashboard.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(DashboardActivity activity);
}
