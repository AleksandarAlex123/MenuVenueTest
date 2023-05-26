package com.aleksandar.menutest.data.repository.dataSourceImpl;

import com.aleksandar.menutest.data.api.VenueApiService;
import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.LoginApiRequest;
import com.aleksandar.menutest.data.model.VenueListApiRequest;
import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.repository.dataSource.RemoteDataSource;

import io.reactivex.Single;


public class RemoteDataSourceImpl implements RemoteDataSource {

    private VenueApiService venueApiService;

    public RemoteDataSourceImpl(VenueApiService venueApiService) {
        this.venueApiService = venueApiService;
    }

    @Override
    public Single<LoginAPiResponse> login(String email, String password) {
        return this.venueApiService.login(new LoginApiRequest(email, password));
    }

    @Override
    public Single<VenueListApiResponse> getVenueList(String latitude, String longitude) {
        return this.venueApiService.getVenueList(new VenueListApiRequest(latitude, longitude));
    }
}
