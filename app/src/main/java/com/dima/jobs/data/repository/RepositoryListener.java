package com.dima.jobs.data.repository;

import com.dima.jobs.data.model.Job;

import java.util.List;

public interface RepositoryListener {

    void onStartDownload();

    void onGetData(List<Job> jobs);

    void onError(String message);

    void onEndDownload();
}
