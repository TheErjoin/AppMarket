package com.example.appmarket.presentation.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.MediatorLiveData;

import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.repository.CheckUpdateServiceRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CheckUpdateService extends Service {

    @Inject
    CheckUpdateServiceRepository repository;

    /**
     * There was a notification, but its meaning was gone, since it was not really needed, it was removed
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediatorLiveData<AppModel> mediatorLiveData = new MediatorLiveData<>();

        ArrayList<AppModel> list = new ArrayList<>(repository.installedApp());
        for (AppModel app : list) {
            mediatorLiveData.addSource(repository.fetchLatestVersion(app.getType()), appModel -> {
            });
        }
        mediatorLiveData.observeForever(appModel -> {
        });
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
