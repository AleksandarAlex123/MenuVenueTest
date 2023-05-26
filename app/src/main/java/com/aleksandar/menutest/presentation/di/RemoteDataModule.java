package com.aleksandar.menutest.presentation.di;


import com.aleksandar.menutest.data.api.VenueApiService;
import com.aleksandar.menutest.data.repository.dataSourceImpl.LoginRemoteDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class RemoteDataModule {

    @Singleton
    @Provides
    LoginRemoteDataSourceImpl provideLoginRemoteDataSource(VenueApiService venueApiService){
        return new LoginRemoteDataSourceImpl(venueApiService);
    }

}
