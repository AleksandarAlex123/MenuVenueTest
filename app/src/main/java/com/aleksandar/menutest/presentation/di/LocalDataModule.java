package com.aleksandar.menutest.presentation.di;

import android.app.Application;

import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.repository.dataSourceImpl.LocalDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalDataModule {

    @Singleton
    @Provides
    LocalDataSource provideLocalDataSource(Application application) {
        return new LocalDataSourceImpl(application);
    }
}
