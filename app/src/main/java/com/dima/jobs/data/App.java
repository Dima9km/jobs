package com.dima.jobs.data;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    public static App instance;

    private JobFavoritesDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, JobFavoritesDatabase.class, "favorites")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public JobFavoritesDatabase getDatabase() {
        return database;
    }
}
