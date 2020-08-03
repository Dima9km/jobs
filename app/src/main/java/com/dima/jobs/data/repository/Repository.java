package com.dima.jobs.data.repository;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.dima.jobs.App;
import com.dima.jobs.R;
import com.dima.jobs.data.database.DatabaseListener;
import com.dima.jobs.data.database.JobFavoritesDao;
import com.dima.jobs.data.database.JobsDatabase;
import com.dima.jobs.data.database.JobsDatabaseDownloader;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.remote.JobsRemoteDownloader;
import com.dima.jobs.data.remote.RemoteListener;

import java.util.List;

public class Repository {

    private RepositoryListener repositoryListener;
    private String location;
    private JobsDatabase db = App.getInstance().getDatabase();
    private JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();

    public Repository(RepositoryListener repositoryListener) {
        this.repositoryListener = repositoryListener;
    }

    public Repository() {
    }

    public void getFavoriteJobs() {
        new JobsDatabaseDownloader().getJobsFromDb(new DatabaseListener() {

            @Override
            public void onStartDownload() {
                repositoryListener.onStartDownload();
            }

            @Override
            public void onGetData(List<Job> jobs) {
                repositoryListener.onGetData(jobs);
            }

            @Override
            public void onError(String message) {
                repositoryListener.onError(message);
            }

            @Override
            public void onEndDownload() {
                repositoryListener.onEndDownload();
            }
        });
    }

    public void getJobs() {
        new JobsRemoteDownloader().getRemoteJobs(new RemoteListener() {

            @Override
            public void onStartDownload() {
                repositoryListener.onStartDownload();
            }

            @Override
            public void onGetData(List<Job> jobs) {
                formatJobs(jobs);
                repositoryListener.onGetData(jobs);
            }

            @Override
            public void onError(String message) {
                repositoryListener.onError(message);
            }

            @Override
            public void onEndDownload() {
                repositoryListener.onEndDownload();
            }
        }, location);
    }

    public void addFavorite(Job job) {
        jobFavoritesDao.addFavorite(job);
    }

    public void deleteFavorite(Job job) {
        jobFavoritesDao.deleteFavorite(job);
    }

    public void formatJobs(List<Job> jobs) {
        for (Job jobDb : jobFavoritesDao.getAll()) {
            for (Job jobServer : jobs) {
                if (jobDb.getId().equals(jobServer.getId())) {
                    jobServer.databaseId = jobDb.getDatabaseId();
                    jobServer.setFavorite(true);
                }
            }
        }
    }

    public void setLocation(SharedPreferences preferences, Resources resources) {
        this.location = resources.getStringArray(R.array.location)[preferences.getInt("user location", 1)];
    }
}