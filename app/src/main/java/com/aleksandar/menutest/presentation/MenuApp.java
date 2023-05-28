package com.aleksandar.menutest.presentation;

import android.app.Application;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MenuApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
    }
}
