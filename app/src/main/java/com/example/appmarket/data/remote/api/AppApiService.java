package com.example.appmarket.data.remote.api;

import com.example.appmarket.domain.models.AppModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AppApiService {

    @GET("software/latest/all")
    Call<List<AppModel>> fetchApps();

    @GET("software/latest/{type}")
    Call<AppModel> fetchLatestTypeVersion(@Path("type") String type);
}
