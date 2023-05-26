package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aleksandar.menutest.domain.usecase.LoginUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private LoginUseCase loginUseCase;

    public ViewModelFactory(Application application, LoginUseCase loginUseCase) {
        this.application = application;
        this.loginUseCase = loginUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(application, loginUseCase);
        } else if (modelClass.isAssignableFrom(VenueViewModel.class)) {
            return (T) new VenueViewModel(application);
        }
        throw new IllegalArgumentException("Unknown View Model Class");
    }
}
