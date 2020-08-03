package com.dima.jobs.data.remote;

import com.dima.jobs.data.model.Job;
import com.dima.jobs.network.JobsApi;
import com.dima.jobs.network.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsRemoteDownloader {

    public void getRemoteJobs(String location, RemoteListener remoteListener) {
        JobsApi jobsApi = NetworkHelper.getInstance().jobsRetrofit.create(JobsApi.class);
        Call<List<Job>> jobsCall = jobsApi.getJobsFromServer(location);
        remoteListener.onStartDownload();

        jobsCall.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                remoteListener.onEndDownload();
                if (response.isSuccessful()) {
                    remoteListener.onGetData(response.body());
                } else {
                    remoteListener.onError("HTTP Error " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                remoteListener.onError(t.getMessage());
            }
        });
    }
}
