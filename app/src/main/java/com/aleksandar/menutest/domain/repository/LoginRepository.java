package com.aleksandar.menutest.domain.repository;

import com.aleksandar.menutest.data.model.LoginAPiResponse;

public interface LoginRepository {
     LoginAPiResponse login(String email, String password);
}
