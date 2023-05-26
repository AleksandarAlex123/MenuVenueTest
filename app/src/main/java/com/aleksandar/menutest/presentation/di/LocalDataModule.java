package com.aleksandar.menutest.presentation.di;

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
    LocalDataSourceImpl provideLocalDataSource() {
        return new LocalDataSourceImpl();
    }


}
