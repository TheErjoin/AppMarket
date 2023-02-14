package com.example.appmarket.data.remote;


import androidx.annotation.NonNull;

import com.example.appmarket.data.remote.service.AppApiService;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class NetworkClient {

    @Inject
    NetworkClient(){}
    private static final Retrofit provideRetrofit =
            NetworkFastBuilder.provideRetrofit(NetworkFastBuilder.provideOkHttpClientBuilder().build());
    public AppApiService provideAppApiService() {
        return provideRetrofit.create(AppApiService.class);
    }
}
