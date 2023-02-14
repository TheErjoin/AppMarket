package com.example.appmarket.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.appmarket.domain.models.AppModel;

import java.util.List;

public interface CheckUpdateServiceRepository {

    LiveData<AppModel> fetchLatestVersion(String type);

    void fetchInstalledApp(AppModel appModel);

    List<AppModel> installedApp();

}
