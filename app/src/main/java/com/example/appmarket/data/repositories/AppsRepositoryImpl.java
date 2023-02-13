package com.example.appmarket.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.appmarket.common.base.BaseRepository;
import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.data.remote.service.AppApiService;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.repository.AppsRepository;

import java.util.List;

public class AppsRepositoryImpl extends BaseRepository implements AppsRepository {

    AppApiService service;

    public AppsRepositoryImpl(AppApiService appApiService) {
        this.service = appApiService;
    }

    @Override
    public LiveData<Resource<List<AppModel>>> fetchApps() {
        return getData(service.getApks());
    }
}
