package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;

public class LoadingAdSplash extends AppCompatActivity {
    private static final long COUNTER_TIME = 2;
    private long secondsRemaining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_ad_splash);
        createTimer(COUNTER_TIME);
    }

    private void createTimer(long seconds){
        CountDownTimer countDownTimer = new CountDownTimer(seconds*1000, 1000) {
            @Override
            public void onTick(long millisUnitFinished) {
                secondsRemaining = ((millisUnitFinished/1000)+1);
            }

            @Override
            public void onFinish() {
                secondsRemaining = 0;
                startMainActivity();
            }
        };
        countDownTimer.start();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}