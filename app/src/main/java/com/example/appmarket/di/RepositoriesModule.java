package com.example.appmarket.di;

import com.example.appmarket.data.repositories.AppsRepositoryImpl;
import com.example.appmarket.domain.repository.AppsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract class RepositoriesModule {

    @Binds
    public abstract AppsRepository bindAppRepositoryImpl(AppsRepositoryImpl repository);

}
