package com.dima.jobs.data.database;

import com.dima.jobs.data.model.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsDatabaseDownloader {

    DbListener dbListener;

    public JobsDatabaseDownloader(DbListener dbListener) {
        this.dbListener = dbListener;
    }

    public void getJobsFromDb() {
        JobsDatabase db = App.getInstance().getDatabase();
        JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();
        Call<List<Job>> jobsCall = jobFavoritesDao.getAll();
        jobsCall.enqueue(new Callback<List<Job>>() {

            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    //TODO
                } else {
                    //TODO
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                //TODO
            }
        });
    }
    }

}
