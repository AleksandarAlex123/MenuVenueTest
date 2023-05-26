package com.aleksandar.menutest.data.repository.dataSourceImpl;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.repository.dataSource.LoginRemoteDataSource;

public class LoginRemoteDataSourceImpl implements LoginRemoteDataSource {

    private LocalDataSource localDataSource;
    private LoginRemoteDataSource loginRemoteDataSource;

    public LoginRemoteDataSourceImpl(LocalDataSource localDataSource, LoginRemoteDataSource loginRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.loginRemoteDataSource = loginRemoteDataSource;
    }

    @Override
    public LoginAPiResponse login(String email, String password) {
        return null;
    }
}
