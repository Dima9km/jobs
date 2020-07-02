package com.dima.jobs;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timeToShow();
    }

    private void timeToShow(){
        //changing view after 3 sec
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(tt, 3000);
    }
}
