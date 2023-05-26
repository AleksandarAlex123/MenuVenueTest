package com.aleksandar.menutest.presentation.di;

import com.aleksandar.menutest.data.VenueRepositoryImpl;
import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.repository.dataSource.RemoteDataSource;
import com.aleksandar.menutest.domain.repository.VenueRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Singleton
    @Provides
    VenueRepository provideVenueRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new VenueRepositoryImpl(localDataSource, remoteDataSource);
    }
}
