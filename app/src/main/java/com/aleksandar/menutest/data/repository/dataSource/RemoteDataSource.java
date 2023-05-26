package com.aleksandar.menutest.data.repository.dataSource;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.VenueListApiResponse;

import io.reactivex.Single;


public interface RemoteDataSource {
    Single<LoginAPiResponse> login(String email, String password);

    Single<VenueListApiResponse> getVenueList(String latitude, String longitude);
}
