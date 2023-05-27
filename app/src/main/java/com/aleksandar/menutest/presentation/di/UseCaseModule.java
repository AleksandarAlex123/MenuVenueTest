package com.aleksandar.menutest.presentation.di;

import com.aleksandar.menutest.domain.repository.VenueRepository;
import com.aleksandar.menutest.domain.usecase.AuthUseCase;
import com.aleksandar.menutest.domain.usecase.GetVenueListUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Singleton
    @Provides
    AuthUseCase provideLoginUseCase(VenueRepository venueRepository) {
        return new AuthUseCase(venueRepository);
    }

    @Singleton
    @Provides
    GetVenueListUseCase provideGetVenueListUseCase(VenueRepository venueRepository) {
        return new GetVenueListUseCase(venueRepository);
    }

}
