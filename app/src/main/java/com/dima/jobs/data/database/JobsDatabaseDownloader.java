package com.dima.jobs.data.database;

import com.dima.jobs.data.model.Job;

import java.util.List;

public class JobsDatabaseDownloader {

    DbListener dbListener;

    public JobsDatabaseDownloader(DbListener dbListener) {
        this.dbListener = dbListener;
    }

    public void getJobsFromDb() {
        JobsDatabase db = App.getInstance().getDatabase();
        JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();
        List<Job> jobsFromDb = jobFavoritesDao.getAll();
        dbListener.onGetData(jobsFromDb);
    }
}
