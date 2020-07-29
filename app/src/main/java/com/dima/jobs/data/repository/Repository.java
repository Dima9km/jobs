package com.dima.jobs.data.repository;

import com.dima.jobs.data.database.DatabaseListener;
import com.dima.jobs.data.database.JobsDatabaseDownloader;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.remote.JobsRemoteDownloader;
import com.dima.jobs.data.remote.RemoteListener;

import java.util.List;

public class Repository {

    private RepositoryListener repositoryListener;

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
            public void onError(Throwable e) {

            }

            @Override
            public void onStartDownload() {

            }

            @Override
            public void onEndDownload() {

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
            public void onError(Throwable e) {

            }

            @Override
            public void onStartDownload() {

            }

            @Override
            public void onEndDownload() {

            }


        }).getRemoteJobs();
    }
}