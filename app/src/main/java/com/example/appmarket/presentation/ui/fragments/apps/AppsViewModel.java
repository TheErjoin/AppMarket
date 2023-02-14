package com.example.appmarket.presentation.ui.fragments.apps;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.usecase.FetchAppsUseCase;

import java.util.List;

import javax.inject.Inject;


public class AppsViewModel extends ViewModel {

    private final FetchAppsUseCase fetchAppsUseCase;
    private MutableLiveData<Resource<List<AppModel>>> _liveData;
    public LiveData<Resource<List<AppModel>>> liveData = _liveData;

    @Inject
    public AppsViewModel(FetchAppsUseCase fetchAppsUseCase, MutableLiveData<Resource<List<AppModel>>> liveData) {
        this.fetchAppsUseCase = fetchAppsUseCase;
        _liveData = liveData;
    }

    public void fetchApps() {
        fetchAppsUseCase.execute().observe((LifecycleOwner) this, listResource -> {
            _liveData.setValue(listResource);
        });
    }
}