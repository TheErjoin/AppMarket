package com.example.appmarket.data.remote.service;

import com.example.appmarket.domain.models.AppModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppApiService {

    @GET("software/latest/all")
    Call<List<AppModel>> getApks();

}
