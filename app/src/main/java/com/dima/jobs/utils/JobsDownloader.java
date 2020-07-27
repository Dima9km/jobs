package com.dima.jobs.utils;

import com.dima.jobs.data.Job;
import com.dima.jobs.network.JobsApi;
import com.dima.jobs.network.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsDownloader {
    private List<Job> jobsNetwork = null;
    private Downloader downloader;

    public JobsDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    public List<Job> getJobs() {
        JobsApi jobsApi = NetworkHelper.getInstance().jobsRetrofit.create(JobsApi.class);
        Call<List<Job>> jobsCall = jobsApi.getJobsFromServer();
        jobsCall.enqueue(new Callback<List<Job>>() {

            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobsNetwork = response.body();
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
            }
        });

        return null;
    }
}
