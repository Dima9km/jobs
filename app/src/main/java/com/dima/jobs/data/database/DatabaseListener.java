package com.dima.jobs.data.database;

import com.dima.jobs.data.model.Job;

import java.util.List;

public interface DatabaseListener {

    void onStartDownload();

    void onGetData(List<Job> jobs);

    void onError(String message);

    void onEndDownload();
}
