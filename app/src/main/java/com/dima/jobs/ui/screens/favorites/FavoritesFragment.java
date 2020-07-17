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
import com.dima.jobs.utils.RecyclerViewAdapter;

public class FavoritesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showJobsList();
    }

    @Override
    public void onResume() {
        super.onResume();
        showJobsList();
    }

    private void showJobsList() {
        RecyclerView recyclerView = getView().findViewById(R.id.rclJobs);
        recyclerView.setAdapter(new RecyclerViewAdapter());
    }
}
