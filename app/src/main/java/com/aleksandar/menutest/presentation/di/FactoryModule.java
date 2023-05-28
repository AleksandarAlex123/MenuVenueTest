package com.aleksandar.menutest.presentation.di;

import android.app.Application;

import com.aleksandar.menutest.domain.usecase.AuthUseCase;
import com.aleksandar.menutest.domain.usecase.GetVenueListUseCase;
import com.aleksandar.menutest.presentation.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FactoryModule {

    @Singleton
    @Provides
    ViewModelFactory provideViewModelFactory(Application app, AuthUseCase authUseCase, GetVenueListUseCase getVenueListUseCase) {
        return new ViewModelFactory(app, authUseCase,getVenueListUseCase);
    }
}
