package com.dima.jobs.network;

import com.dima.jobs.data.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JobsApi {
    @GET("/positions.json")
    Call<List<Job>> getJobsFromServer();
}
