package com.example.appmarket.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.appmarket.common.base.BaseRepository;
import com.example.appmarket.data.remote.api.AppApiService;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.repository.CheckUpdateServiceRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CheckUpdateServiceRepositoryImpl extends BaseRepository implements CheckUpdateServiceRepository {

    AppApiService service;
    ArrayList<AppModel> installedAppList = new ArrayList<>();

    @Inject
    public CheckUpdateServiceRepositoryImpl(AppApiService appApiService) {
        this.service = appApiService;
    }

    @Override
    public LiveData<AppModel> fetchLatestVersion(String type) {
        return doRequestWithoutResource(service.fetchLatestTypeVersion(type));
    }

    @Override
    public void fetchInstalledApp(AppModel appModel) {
        installedAppList.add(appModel);
    }

    @Override
    public List<AppModel> installedApp() {
        return installedAppList;
    }
}
