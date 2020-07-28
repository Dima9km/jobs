package com.dima.jobs.ui.screens.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.database.App;
import com.dima.jobs.data.model.Job;
import com.dima.jobs.data.database.JobFavoritesDao;
import com.dima.jobs.data.database.JobsDatabase;
import com.dima.jobs.utils.FragmentRefresher;
import com.dima.jobs.utils.JobsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment implements FragmentRefresher {

    JobsDatabase db = App.getInstance().getDatabase();
    JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        showFavoritesList();
    }

    private void showFavoritesList() {
        List<Job> jobsDb = jobFavoritesDao.getAll();
        RecyclerView recyclerJobs = getView().findViewById(R.id.rvJobs);

        TextView emptyText = getView().findViewById(R.id.tvEmpty);
        recyclerJobs.setVisibility(jobsDb.isEmpty() ? View.GONE : View.VISIBLE);
        emptyText.setVisibility(jobsDb.isEmpty() ? View.VISIBLE : View.GONE);

        recyclerJobs.setAdapter(new JobsAdapter(jobsDb,  this));
    }

    @Override
    public void onDataChanged() {
        showFavoritesList();
    }
}
