package com.aleksandar.menutest.data.repository;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.repository.dataSource.RemoteDataSource;
import com.aleksandar.menutest.data.util.AppConstant;
import com.aleksandar.menutest.domain.repository.VenueRepository;

import io.reactivex.Single;

public class VenueRepositoryImpl implements VenueRepository {

    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    public VenueRepositoryImpl(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Single<VenueListApiResponse> getVenueList(String latitude, String longitude) {
        return this.remoteDataSource.getVenueList(latitude, longitude);
    }

    @Override
    public Single<LoginAPiResponse> login(String email, String password) {
        return this.remoteDataSource.login(email, password);
    }

    @Override
    public void saveAccessToken(String accessToken) {
        localDataSource.saveData(AppConstant.ACCESS_TOKEN_KEY, accessToken);
    }

    @Override
    public void logOut() {
        localDataSource.clearData(AppConstant.ACCESS_TOKEN_KEY);
    }

    @Override
    public boolean isUserLoggedIn() {
        return localDataSource.isUserLogedIn();
    }
}
