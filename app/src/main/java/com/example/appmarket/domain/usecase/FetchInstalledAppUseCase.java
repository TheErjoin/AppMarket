package com.example.appmarket.domain.usecase;

import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.repository.CheckUpdateServiceRepository;

import javax.inject.Inject;

public class FetchInstalledAppUseCase {

    private final CheckUpdateServiceRepository repository;

    @Inject
    public FetchInstalledAppUseCase(CheckUpdateServiceRepository repository) {
        this.repository = repository;
    }

    public void invoke(AppModel appModel) {
        repository.fetchInstalledApp(appModel);
    }
}
