package com.aleksandar.menutest.data;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.repository.dataSource.LoginRemoteDataSource;
import com.aleksandar.menutest.domain.repository.VenueRepository;

public class VenueRepositoryImpl implements VenueRepository {
    private LocalDataSource localDataSource;
    private LoginRemoteDataSource loginRemoteDataSource;

    public VenueRepositoryImpl(LocalDataSource localDataSource, LoginRemoteDataSource loginRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.loginRemoteDataSource = loginRemoteDataSource;
    }

    @Override
    public VenueListApiResponse getVenueList(String latitude, String longitude) {
        return null;
    }

    @Override
    public LoginAPiResponse login(String email, String password) {
        return null;
    }
}
