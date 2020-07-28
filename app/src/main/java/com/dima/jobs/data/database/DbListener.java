package com.dima.jobs.data.database;

import com.dima.jobs.data.model.Job;

import java.util.List;

public interface DbListener {
    void onGetDbData(List<Job> jobs);

}
