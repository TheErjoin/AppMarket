package com.example.appmarket.presentation.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;

import com.example.appmarket.R;
import com.example.appmarket.presentation.ui.service.CheckUpdateService;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceLaunch();
    }

    private void serviceLaunch() {
        Intent service = new Intent(this, CheckUpdateService.class);
        startService(service);
    }
}