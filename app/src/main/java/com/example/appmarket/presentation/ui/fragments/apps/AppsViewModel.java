package com.example.appmarket.presentation.ui.fragments.apps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.usecase.FetchAppsUseCase;
import com.example.appmarket.domain.usecase.FetchInstalledAppUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AppsViewModel extends ViewModel {

    private final FetchAppsUseCase fetchAppsUseCase;
    private final FetchInstalledAppUseCase fetchInstalledAppUseCase;
    private final MutableLiveData<Resource<List<AppModel>>> _liveData = new MutableLiveData<>();
    public LiveData<Resource<List<AppModel>>> liveData = _liveData;
    @Inject
    public AppsViewModel(FetchAppsUseCase fetchAppsUseCase, FetchInstalledAppUseCase fetchInstalledAppUseCase) {
        this.fetchAppsUseCase = fetchAppsUseCase;
        this.fetchInstalledAppUseCase = fetchInstalledAppUseCase;
    }

    public void fetchApps() {
        fetchAppsUseCase.invoke().observeForever(_liveData::postValue);
    }

    public void fetchInstalledApp(AppModel appModel) {
        fetchInstalledAppUseCase.invoke(appModel);
    }
}