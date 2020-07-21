package com.dima.jobs.data;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    public static App instance;

    private JobsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, JobsDatabase.class, "favorites")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public JobsDatabase getDatabase() {
        return database;
    }
}
