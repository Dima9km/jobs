package com.dima.jobs.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JobFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavorite(Job job);

    @Delete
    void deleteFavorite(Job job);

    @Query("SELECT * FROM favorites WHERE id = :id LIMIT 1")
    Job getJobById(String id);

    @Query("SELECT * FROM favorites")
    List<Job> getAll();


}
