package com.dima.jobs.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dima.jobs.data.model.Job;

@Database(entities = {Job.class}, version = 1)
public abstract class JobsDatabase extends RoomDatabase {

    public abstract JobFavoritesDao jobFavoritesDao();
}
