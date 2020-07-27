package com.dima.jobs.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    public static NetworkHelper retrofitInstance;
    private static final String BASEURL = "https://jobs.github.com";
    public Retrofit jobsRetrofit;
    public NetworkHelper() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create();
        jobsRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static NetworkHelper getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new NetworkHelper();
        }
        return retrofitInstance;
    }
}
