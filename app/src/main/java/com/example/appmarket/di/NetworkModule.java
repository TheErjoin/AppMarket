package com.example.appmarket.di;

import com.example.appmarket.data.remote.NetworkClient;
import com.example.appmarket.data.remote.service.AppApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public AppApiService provideAppsApiService(NetworkClient networkClient) {
        return networkClient.provideAppApiService();
    }

}
