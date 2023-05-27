package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aleksandar.menutest.domain.usecase.AuthUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private AuthUseCase authUseCase;

    public ViewModelFactory(Application application, AuthUseCase authUseCase) {
        this.application = application;
        this.authUseCase = authUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(application, authUseCase);
        } else if (modelClass.isAssignableFrom(VenueViewModel.class)) {
            return (T) new VenueViewModel(application);
        }
        throw new IllegalArgumentException("Unknown View Model Class");
    }
}
