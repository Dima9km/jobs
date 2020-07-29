package com.dima.jobs.ui.screens.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.database.App;
import com.dima.jobs.data.database.JobFavoritesDao;
import com.dima.jobs.data.database.JobsDatabase;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.repository.Repository;
import com.dima.jobs.data.repository.RepositoryListener;
import com.dima.jobs.utils.JobsAdapter;

import java.util.List;

public class JobsFragment extends Fragment {

    Repository repository = new Repository(new RepositoryListener() {
        @Override
        public void onGetData(List<Job> jobs) {
            showJobsList(jobs);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onStartDownload() {

        }

        @Override
        public void onEndDownload() {

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        repository.getJobs();
    }

    private void showJobsList(List<Job> jobsServer) {
        RecyclerView recyclerJobs = getView().findViewById(R.id.rvJobs);
        recyclerJobs.setAdapter(new JobsAdapter(jobsServer));
    }

    private List<Job> formatJobs(List<Job> jobs) {
        List<Job> jobsServer = jobs;
        JobsDatabase db = App.getInstance().getDatabase();
        JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();
        List<Job> jobsDb = jobFavoritesDao.getAll();

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