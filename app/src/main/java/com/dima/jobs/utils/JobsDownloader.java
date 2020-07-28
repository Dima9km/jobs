package com.dima.jobs.utils;

import com.dima.jobs.data.Job;
import com.dima.jobs.network.JobsApi;
import com.dima.jobs.network.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsDownloader {

    Listener listener;

    public JobsDownloader(Listener listener) {
        this.listener = listener;
    }

    public void getJobs() {
        JobsApi jobsApi = NetworkHelper.getInstance().jobsRetrofit.create(JobsApi.class);
        Call<List<Job>> jobsCall = jobsApi.getJobsFromServer();
        jobsCall.enqueue(new Callback<List<Job>>() {

            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    listener.showLoader(false);
                    listener.onGetData(response.body());
                } else {
                    listener.showLoader(false);
                    listener.showMessage("Empty Data");
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                listener.showMessage(t.getMessage());
            }
        });
    }
}
