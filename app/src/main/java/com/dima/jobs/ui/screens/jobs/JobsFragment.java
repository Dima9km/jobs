package com.dima.jobs.ui.screens.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.App;
import com.dima.jobs.data.Job;
import com.dima.jobs.data.JobFavoritesDao;
import com.dima.jobs.data.JobsDatabase;
import com.dima.jobs.utils.JobsAdapter;
import com.dima.jobs.utils.JobsDownloader;
import com.dima.jobs.utils.Listener;

import java.util.List;

public class JobsFragment extends Fragment {

    JobsDownloader jobsDownloader = new JobsDownloader(new Listener() {
        @Override
        public void showLoader(boolean visible) {
            ProgressBar progressBar = getActivity().findViewById(R.id.pbJobs);
            progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onGetData(List<Job> jobs) {
            showJobsList(formatJobs(jobs));
        }

        @Override
        public void showMessage(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        jobsDownloader.getJobs();
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