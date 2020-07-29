package com.dima.jobs.data.remote;

import com.dima.jobs.data.model.Job;

import java.util.List;

public interface RemoteListener {

    void onGetData(List<Job> jobs);

    void onError(String message);

    void onStartDownload();

    void onEndDownload();

}
