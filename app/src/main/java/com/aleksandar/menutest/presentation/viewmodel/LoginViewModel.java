package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.usecase.AuthUseCase;

public class LoginViewModel extends AndroidViewModel {
    private AuthUseCase authUseCase;

    public LoginViewModel(@NonNull Application application, AuthUseCase authUseCase) {
        super(application);
        this.authUseCase = authUseCase;
    }

    public void login(String email, String password) {
        authUseCase.login(email, password);
    }

    public boolean isUserLoggedIn() {
        return authUseCase.isUserLoggedIn();
    }

    public LiveData<Resource<LoginAPiResponse>> getLoginAPiResponseLiveData() {
        return authUseCase.getLoginAPiResponseLiveData();
    }
    public void clearDisposable() {
        authUseCase.clearDisposable();
    }
}
