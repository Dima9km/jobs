package com.dima.jobs.ui.screens.jobs;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dima.jobs.R;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.repository.Repository;
import com.dima.jobs.data.repository.RepositoryListener;

import java.util.List;

public class JobsFragment extends Fragment {

    private RecyclerView recyclerJobs;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar circleProgress;

    private Repository repository = new Repository(new RepositoryListener() {
        @Override
        public void onStartDownload() {
            if (recyclerJobs.getAdapter() != null && recyclerJobs.getAdapter().getItemCount() > 0) {
                circleProgress.setVisibility(View.GONE);
            } else {
                circleProgress.setVisibility(View.VISIBLE);
            }
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
            circleProgress.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository.setPreferences(PreferenceManager.getDefaultSharedPreferences(getContext()));
        repository.setResources(getContext().getResources());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        repository.getJobs();
    }

    private void initUI() {
        recyclerJobs = getView().findViewById(R.id.rvJobs);
        circleProgress = getView().findViewById(R.id.pbJobs);
        swipeRefreshLayout = getView().findViewById(R.id.srlRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repository.getJobs();
            }
        });
    }

    private void showJobsList(List<Job> jobsServer) {
        recyclerJobs.setAdapter(new JobsAdapter(jobsServer, repository));
    }
}