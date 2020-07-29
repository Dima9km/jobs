package com.dima.jobs.data.database;

import com.dima.jobs.data.model.Job;

import java.util.List;

public class JobsDatabaseDownloader {

    DatabaseListener databaseListener;

    public JobsDatabaseDownloader(DatabaseListener databaseListener) {
        this.databaseListener = databaseListener;
    }

    public void getJobsFromDb() {
        databaseListener.onStartDownload();
        JobsDatabase db = App.getInstance().getDatabase();
        JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();
        List<Job> jobsFromDb = jobFavoritesDao.getAll();
        databaseListener.onGetData(jobsFromDb);
        databaseListener.onEndDownload();
    }
}
