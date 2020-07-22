package com.dima.jobs.ui.screens.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.App;
import com.dima.jobs.data.Job;
import com.dima.jobs.data.JobFavoritesDao;
import com.dima.jobs.data.JobsDatabase;
import com.dima.jobs.utils.JobsAdapter;
import com.dima.jobs.utils.JobsDataCreator;

import java.util.List;

public class JobsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        showJobsList();
    }

    private void showJobsList() {
        RecyclerView recyclerJobs = getView().findViewById(R.id.rvJobs);
        recyclerJobs.setAdapter(new JobsAdapter(updateFavorites(), null));
    }

    private List<Job> updateFavorites() {
        JobsDatabase db = App.getInstance().getDatabase();
        JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();
        List<Job> jobsDb = jobFavoritesDao.getAll();
        List<Job> jobsServer = new JobsDataCreator().getVacanciesList();
        for (Job jobDb : jobsDb) {
            for (Job jobServer : jobsServer) {
                if (jobDb.getId().equals(jobServer.getId())) {
                    jobServer.databaseId = jobDb.getDatabaseId();
                    jobServer.setFavorite(true);
                }
            }
        }
        return jobsServer;
    }
}