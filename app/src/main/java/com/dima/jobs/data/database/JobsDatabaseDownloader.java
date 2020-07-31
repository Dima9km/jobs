package com.dima.jobs.data.database;

import com.dima.jobs.App;

public class JobsDatabaseDownloader {

    public void getJobsFromDb(DatabaseListener databaseListener) {
        databaseListener.onStartDownload();
        databaseListener.onGetData(App.getInstance().getDatabase().jobFavoritesDao().getAll());
        databaseListener.onEndDownload();
    }
}
