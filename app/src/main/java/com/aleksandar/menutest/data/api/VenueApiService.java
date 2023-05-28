package com.aleksandar.menutest.data.api;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.LoginApiRequest;
import com.aleksandar.menutest.data.model.VenueListApiRequest;
import com.aleksandar.menutest.data.model.VenueListApiResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VenueApiService {

    @Headers({
            "Content-Type: application/json",
            "application: mobile-application",
            "Device-UUID: 123456",
            "Api-Version: 3.7.0",
    })
    @POST("api/customers/login")
    Single<LoginAPiResponse> login(@Body LoginApiRequest loginApiRequest);

    @Headers({
            "Content-Type: application/json",
            "application: mobile-application",
            "Device-UUID: 123456",
            "Api-Version: 3.7.0",
    })
    @POST("api/directory/search")
    Single<VenueListApiResponse> getVenueList(@Body VenueListApiRequest venueListApiRequest);

}
