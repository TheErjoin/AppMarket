package com.example.appmarket.di;

import android.content.Context;

import com.example.appmarket.data.remote.service.AppApiService;
import com.example.appmarket.data.remote.service.mock.AppsApiServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public AppApiService provideAppsApiService(@ApplicationContext Context context) {
        return new AppsApiServiceImpl(context);
    }

}
