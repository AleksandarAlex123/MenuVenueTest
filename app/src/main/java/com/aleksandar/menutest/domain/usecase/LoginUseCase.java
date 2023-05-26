package com.aleksandar.menutest.domain.usecase;

import com.aleksandar.menutest.domain.repository.LoginRepository;

public class LoginUseCase {
    private LoginRepository loginRepository;

    public LoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
}
