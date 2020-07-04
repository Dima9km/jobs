package com.dima.jobs.ui.screens.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dima.jobs.R;
import com.dima.jobs.ui.screens.jobs.JobsActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timeToShow();
    }

    private void timeToShow() {
        //changing view after 3 sec
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, JobsActivity.class));
                finish();
            }
        }, 3000);
    }
}
