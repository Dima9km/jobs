package com.dima.jobs.data.remote;

import com.dima.jobs.data.model.Job;
import com.dima.jobs.network.JobsApi;
import com.dima.jobs.network.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsRemoteDownloader {

    RemoteListener remoteListener;

    public JobsRemoteDownloader(RemoteListener remoteListener) {
        this.remoteListener = remoteListener;
    }

    public void getRemoteJobs() {
        JobsApi jobsApi = NetworkHelper.getInstance().jobsRetrofit.create(JobsApi.class);
        Call<List<Job>> jobsCall = jobsApi.getJobsFromServer();
        jobsCall.enqueue(new Callback<List<Job>>() {

            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    remoteListener.onGetData(response.body());
                } else {
//                    remoteListener.showLoader(false);
//                    remoteListener.showMessage("Empty Data");
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
//                remoteListener.showMessage(t.getMessage());
            }
        });
    }
}
