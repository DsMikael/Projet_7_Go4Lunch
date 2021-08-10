package com.mdasilva.go4lunch;

import android.app.Application;

import timber.log.Timber;

public class Go4LunchApp extends Application {
    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}