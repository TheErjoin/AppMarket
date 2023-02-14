package com.example.appmarket.data.remote.service.mock;

import android.content.Context;

import com.example.appmarket.common.utils.JsonConverterUtils;
import com.example.appmarket.data.remote.service.AppApiService;
import com.example.appmarket.domain.models.AppModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class AppsApiServiceImpl implements AppApiService {

    Context context;
    @Inject

    public AppsApiServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public Call<List<AppModel>> getApks() {
        return JsonConverterUtils.fromJson(JsonConverterUtils.jsonFromAssets(context, "apps.json"));
    }
}
