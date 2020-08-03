package com.dima.jobs.network;

import com.dima.jobs.data.model.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobsApi {
    @GET("/positions.json")
    Call<List<Job>> getJobsFromServer(@Query("location") String location);
}
