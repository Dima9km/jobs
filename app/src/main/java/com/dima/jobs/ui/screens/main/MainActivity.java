package com.dima.jobs.ui.screens.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.dima.jobs.R;
import com.dima.jobs.ui.screens.favorites.FavoritesFragment;
import com.dima.jobs.ui.screens.jobs.JobsFragment;
import com.dima.jobs.ui.screens.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final JobsFragment jobsFragment = new JobsFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        final FavoritesFragment favoritesFragment = new FavoritesFragment();

        changeFragment(jobsFragment);

        final Toolbar toolbar = findViewById(R.id.tbMain);
        toolbar.setTitle("Jobs list");

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.naviJobs:
                        changeFragment(jobsFragment);
                        toolbar.setTitle("Jobs list");
                        break;
                    case R.id.naviProfile:
                        changeFragment(profileFragment);
                        toolbar.setTitle("Profile");
                        break;
                    case R.id.naviFavorites:
                        changeFragment(favoritesFragment);
                        toolbar.setTitle("Favorites");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void changeFragment(Fragment toChange) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragmentContainer, toChange).commit();
    }


}
