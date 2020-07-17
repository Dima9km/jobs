package com.dima.jobs.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Job.class}, version = 1)
public abstract class JobFavoritesDatabase extends RoomDatabase {

    public abstract JobFavoritesDao jobFavoritesDao();
}
