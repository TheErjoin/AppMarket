package com.example.appmarket.presentation.ui.fragments.apps;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.domain.models.AppModel;
import com.example.appmarket.domain.usecase.FetchAppsUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AppsViewModel extends ViewModel {

    private final FetchAppsUseCase fetchAppsUseCase;
    private final MutableLiveData<Resource<List<AppModel>>> _liveData = new MutableLiveData<>();
    public LiveData<Resource<List<AppModel>>> liveData = _liveData;

    @Inject
    public AppsViewModel(FetchAppsUseCase fetchAppsUseCase) {
        this.fetchAppsUseCase = fetchAppsUseCase;
    }

    public void fetchApps() {
        fetchAppsUseCase.execute().observeForever(_liveData::postValue);
        Log.e("TAG", "fetchApps: " + fetchAppsUseCase.execute().getValue().msg );
    }
}