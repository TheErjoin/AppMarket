package com.example.appmarket.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.appmarket.common.utils.Resource;
import com.example.appmarket.domain.models.AppModel;

import java.util.List;

public interface AppsRepository {

    LiveData<Resource<List<AppModel>>> fetchApps();

}
