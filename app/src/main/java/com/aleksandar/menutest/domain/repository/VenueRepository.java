package com.aleksandar.menutest.domain.repository;

import com.aleksandar.menutest.data.model.VenueListApiResponse;

public interface VenueRepository {
    VenueListApiResponse getVenueList(String latitude, String longitude);
}
