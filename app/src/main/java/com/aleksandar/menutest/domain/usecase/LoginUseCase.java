package com.aleksandar.menutest.domain.usecase;

public class LoginUseCase {
    private LoginRepository loginRepository;

    public LoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
}
