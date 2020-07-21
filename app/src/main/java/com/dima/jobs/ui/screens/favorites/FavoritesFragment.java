package com.dima.jobs.ui.screens.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.App;
import com.dima.jobs.data.Job;
import com.dima.jobs.data.JobFavoritesDao;
import com.dima.jobs.data.JobFavoritesDatabase;
import com.dima.jobs.utils.JobsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment {

    JobFavoritesDatabase jobFavoritesDatabase = App.getInstance().getDatabase();
    JobFavoritesDao jobFavoritesDao = jobFavoritesDatabase.jobFavoritesDao();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showFavoritesList();
    }

    @Override
    public void onResume() {
        super.onResume();
        showFavoritesList();
    }

    private void showFavoritesList() {
        List<Job> jobsDb = jobFavoritesDao.getAll();
        RecyclerView recyclerJobs = getView().findViewById(R.id.rvJobs);
        recyclerJobs.setAdapter(new JobsAdapter(jobsDb));
    }
}
