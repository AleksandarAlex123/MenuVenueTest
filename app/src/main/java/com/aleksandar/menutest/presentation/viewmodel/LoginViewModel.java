package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.usecase.LoginUseCase;

public class LoginViewModel extends AndroidViewModel {
    private Application application;
    private LoginUseCase loginUseCase;

    private MutableLiveData<Resource<LoginAPiResponse>> loginMutableLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application, LoginUseCase loginUseCase) {
        super(application);
        this.loginUseCase = loginUseCase;
    }

    void login(String email, String password){
        loginMutableLiveData.postValue(Resource.loading());
    }
}
