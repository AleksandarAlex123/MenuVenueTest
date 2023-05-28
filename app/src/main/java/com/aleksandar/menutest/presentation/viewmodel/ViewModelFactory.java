package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aleksandar.menutest.domain.usecase.AuthUseCase;
import com.aleksandar.menutest.domain.usecase.GetVenueListUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private AuthUseCase authUseCase;
    private GetVenueListUseCase getVenueListUseCase;

    public ViewModelFactory(Application application, AuthUseCase authUseCase, GetVenueListUseCase getVenueListUseCase) {
        this.application = application;
        this.authUseCase = authUseCase;
        this.getVenueListUseCase = getVenueListUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(application, authUseCase);
        } else if (modelClass.isAssignableFrom(VenueViewModel.class)) {
            return (T) new VenueViewModel(application,authUseCase,getVenueListUseCase);
        }
        throw new IllegalArgumentException("Unknown View Model Class");
    }
}
