package com.example.appmarket.presentation.ui.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.appmarket.R;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.repository.CheckUpdateServiceRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CheckUpdateService extends Service {

    @Inject
    CheckUpdateServiceRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        MutableLiveData<AppModel> liveData = new MutableLiveData<>();
        ArrayList<AppModel> list = new ArrayList<>(repository.installedApp());
        for (AppModel app : list) {
            repository.fetchLatestVersion(app.getType());
        }
        liveData.observeForever(this::notificationBuild);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    private void notificationBuild(AppModel appModel) {
        String channelId = "app_updates";
        String channelName = "Application Updates";

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(getString(R.string.search_updates))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(appModel.getTitle())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(getString(R.string.notifications_about_available_updates_for_installed_applications));
            notificationManager.createNotificationChannel(notificationChannel);
        }

        int notificationId = 1;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
