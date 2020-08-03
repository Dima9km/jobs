package com.dima.jobs.ui.screens.jobs;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.repository.Repository;
import com.dima.jobs.data.repository.RepositoryListener;

import java.util.List;

public class JobsFragment extends Fragment {

    private Repository repository = new Repository(new RepositoryListener() {
        @Override
        public void onStartDownload() {
            getView().findViewById(R.id.pbJobs).setVisibility(View.VISIBLE);
        }

        @Override
        public void onGetData(List<Job> jobs) {
            showJobsList(jobs);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onEndDownload() {
            getView().findViewById(R.id.pbJobs).setVisibility(View.GONE);
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository.setLocation(PreferenceManager.getDefaultSharedPreferences(getContext()), getContext().getResources());
    }

    @Override
    public void onResume() {
        super.onResume();
        repository.getJobs();
    }

    private void showJobsList(List<Job> jobsServer) {
        RecyclerView recyclerJobs = getView().findViewById(R.id.rvJobs);
        recyclerJobs.setAdapter(new JobsAdapter(jobsServer, repository));
    }
}