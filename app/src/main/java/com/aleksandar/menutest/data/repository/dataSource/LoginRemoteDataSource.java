package com.aleksandar.menutest.data.repository.dataSource;

import com.aleksandar.menutest.data.model.LoginAPiResponse;

public interface LoginRemoteDataSource {
    LoginAPiResponse login(String email, String password);
}
