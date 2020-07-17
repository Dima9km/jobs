package com.dima.jobs.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface JobFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavorite(Job job);

    @Delete
    void deleteFavorite(Job job);

}
