package com.dima.jobs.ui.screens.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.ui.screens.jobs.adapter.JobsAdapter;
import com.dima.jobs.ui.screens.profile.ProfileActivity;

public class JobsActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        initUI();
        showJobsList();
    }

    private void initUI() {
        toolbar = findViewById(R.id.tbJobs);
        toolbar.setOnMenuItemClickListener((new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btnEditProfile) {
                    startActivity(new Intent(toolbar.getContext(), ProfileActivity.class));
                }
                return true;
            }
        }));
    }

    private void showJobsList() {
        RecyclerView recyclerView = findViewById(R.id.rclJobs);
        recyclerView.setAdapter(new JobsAdapter());
    }
}