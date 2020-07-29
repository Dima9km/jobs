package com.dima.jobs.data.repository;

import com.dima.jobs.data.database.App;
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
    private JobsDatabase db = App.getInstance().getDatabase();
    private JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();

    public Repository(RepositoryListener repositoryListener) {
        this.repositoryListener = repositoryListener;
    }

    public void getFavoriteJobs() {
        new JobsDatabaseDownloader(new DatabaseListener() {

            @Override
            public void onGetData(List<Job> jobs) {
                repositoryListener.onGetData(jobs);
            }

            @Override
            public void onError(String message) {
                repositoryListener.onError(message);
            }

            @Override
            public void onStartDownload() {
                repositoryListener.onStartDownload();
            }

            @Override
            public void onEndDownload() {
                repositoryListener.onEndDownload();
            }
        }).getJobsFromDb();
    }

    public void getJobs() {
        new JobsRemoteDownloader(new RemoteListener() {

            @Override
            public void onGetData(List<Job> jobs) {
                repositoryListener.onGetData(jobs);
            }

            @Override
            public void onError(String message) {
                repositoryListener.onError(message);
            }

            @Override
            public void onStartDownload() {
                repositoryListener.onStartDownload();
            }

            @Override
            public void onEndDownload() {
                repositoryListener.onEndDownload();
            }
        }).getRemoteJobs();
    }

    public void addFavorite(Job job) {
        jobFavoritesDao.addFavorite(job);
    }

    public void deleteFavorite(Job job) {
        jobFavoritesDao.deleteFavorite(job);
    }

    public List<Job> formatJobs(List<Job> jobs) {
        List<Job> jobsDb = jobFavoritesDao.getAll();

        for (Job jobDb : jobsDb) {
            for (Job jobServer : jobs) {
                if (jobDb.getId().equals(jobServer.getId())) {
                    jobServer.databaseId = jobDb.getDatabaseId();
                    jobServer.setFavorite(true);
                }
            }
        }
        return jobs;
    }
}