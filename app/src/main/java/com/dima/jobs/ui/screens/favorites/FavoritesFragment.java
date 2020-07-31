package com.dima.jobs.ui.screens.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.repository.Repository;
import com.dima.jobs.data.repository.RepositoryListener;
import com.dima.jobs.utils.FragmentRefresher;
import com.dima.jobs.ui.screens.jobs.JobsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment implements FragmentRefresher {

    private Repository repository = new Repository(new RepositoryListener() {
        @Override
        public void onStartDownload() {
            //todo
        }

        @Override
        public void onGetData(List<Job> jobs) {
            showFavoritesList(jobs);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onEndDownload() {
            //todo
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        repository.getFavoriteJobs();
    }

    @Override
    public void onDataChanged() {
        repository.getFavoriteJobs();
    }

    private void showFavoritesList(List<Job> jobs) {
        RecyclerView recyclerJobs = getView().findViewById(R.id.rvJobs);
        TextView emptyText = getView().findViewById(R.id.tvEmpty);

        recyclerJobs.setVisibility(jobs.isEmpty() ? View.GONE : View.VISIBLE);
        emptyText.setVisibility(jobs.isEmpty() ? View.VISIBLE : View.GONE);

        recyclerJobs.setAdapter(new JobsAdapter(jobs, this, repository));
    }
}
