package com.dima.jobs.data.remote;

import com.dima.jobs.data.model.Job;

import java.util.List;

public interface RemoteListener {

    void showLoader(boolean visible);

    void onGetData(List<Job> jobs);

    void showMessage(String message);
}
