package com.dima.jobs.network;

import com.dima.jobs.data.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    private static NetworkHelper jobsInstance;
    private static final String BASE_URL = "https://jobs.github.com/positions.json";
    private Retrofit jobsRetrofit;

    private NetworkHelper() {
        jobsRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkHelper getInstance() {
        if (jobsInstance == null) {
            jobsInstance = new NetworkHelper();
        }
        return jobsInstance;
    }

    JobsApi jobsApi = getInstance().jobsRetrofit.create(JobsApi.class);
    Call<List<Job>> jobs = jobsApi.getAllJobs();
    jobs.e

}
