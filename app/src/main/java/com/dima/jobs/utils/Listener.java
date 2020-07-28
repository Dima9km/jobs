package com.dima.jobs.utils;

import com.dima.jobs.data.Job;

import java.util.List;

public interface Listener {

    void showLoader(boolean visible);

    void onGetData(List<Job> jobs);

    void showMessage(String message);
}
