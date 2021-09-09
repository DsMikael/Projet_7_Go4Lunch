package com.mdasilva.go4lunch;

import android.app.Application;

import com.google.android.libraries.places.api.Places;

import timber.log.Timber;

public class Go4LunchApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        Places.initialize(this, getString(R.string.google_maps_key));

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}