package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.usecase.LoginUseCase;

public class LoginViewModel extends AndroidViewModel {
    private LoginUseCase loginUseCase;

    public LoginViewModel(@NonNull Application application, LoginUseCase loginUseCase) {
        super(application);
        this.loginUseCase = loginUseCase;
    }

    void login(String email, String password) {
        loginUseCase.login(email, password);
    }

    public LiveData<Resource<LoginAPiResponse>> getLoginAPiResponseLiveData() {
        return loginUseCase.getLoginAPiResponseLiveData();
    }
    public void clearDisposable() {
        loginUseCase.clearDisposable();
    }
}
