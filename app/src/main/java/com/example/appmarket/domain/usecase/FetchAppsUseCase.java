package com.example.appmarket.domain.usecase;

import androidx.lifecycle.LiveData;

import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.repository.AppsRepository;

import java.util.List;

import javax.inject.Inject;

public class FetchAppsUseCase {

    private final AppsRepository repository;

    @Inject
    public FetchAppsUseCase(AppsRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<AppModel>>> execute() {
        return repository.fetchApps();
    }
}
