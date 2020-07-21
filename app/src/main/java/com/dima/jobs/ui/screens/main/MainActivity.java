package com.dima.jobs.ui.screens.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dima.jobs.R;
import com.dima.jobs.ui.screens.favorites.FavoritesFragment;
import com.dima.jobs.ui.screens.jobs.JobsFragment;
import com.dima.jobs.ui.screens.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final JobsFragment jobsFragment = new JobsFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        final FavoritesFragment favoritesFragment = new FavoritesFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, jobsFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.naviJobs:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, jobsFragment).commit();
                        break;
                    case R.id.naviProfile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                        break;
                    case R.id.naviFavorites:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, favoritesFragment).commit();
                        break;
                }
                return true;
            }
        });
    }
}
