package com.example.appmarket.data.remote;


import com.example.appmarket.data.remote.api.AppApiService;

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
