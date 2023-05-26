package com.aleksandar.menutest.domain.repository;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.VenueListApiResponse;

public interface VenueRepository {
    VenueListApiResponse getVenueList(String latitude, String longitude);
    LoginAPiResponse login(String email, String password);
}
