package com.dima.jobs.data.repository;

import com.dima.jobs.data.model.Job;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    List<Job> jobsDb = new ArrayList<>();
    List<Job> jobsServer = new ArrayList<>();
    RepositoryListener repositoryListener;

    public Repository(RepositoryListener repositoryListener) {
        this.repositoryListener = repositoryListener;
    }

    public void getJobs() {
        //TODO
    }
}
