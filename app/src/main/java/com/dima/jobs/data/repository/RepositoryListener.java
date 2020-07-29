package com.dima.jobs.data.repository;

import com.dima.jobs.data.model.Job;

import java.util.List;

public interface RepositoryListener {

    void onGetData(List<Job> jobs);

    void onError(Throwable e);

    void onStartDownload();

    void onEndDownload();
}
