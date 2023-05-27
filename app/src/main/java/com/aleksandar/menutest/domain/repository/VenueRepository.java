package com.aleksandar.menutest.domain.repository;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.VenueListApiResponse;

import io.reactivex.Single;


public interface VenueRepository {

    Single<VenueListApiResponse> getVenueList(String latitude, String longitude);
    Single<LoginAPiResponse> login(String email, String password);
    void saveAccessToken(String accessToken);
}
