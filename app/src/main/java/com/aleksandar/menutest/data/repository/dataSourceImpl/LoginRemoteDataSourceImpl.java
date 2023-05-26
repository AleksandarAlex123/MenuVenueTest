package com.aleksandar.menutest.data.repository.dataSourceImpl;

import com.aleksandar.menutest.data.api.VenueApiService;
import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.repository.dataSource.LoginRemoteDataSource;

public class LoginRemoteDataSourceImpl implements LoginRemoteDataSource {

    private VenueApiService venueApiService;

    public LoginRemoteDataSourceImpl(VenueApiService venueApiService) {
        this.venueApiService = venueApiService;
    }

    @Override
    public LoginAPiResponse login(String email, String password) {
        return null;
    }
}
