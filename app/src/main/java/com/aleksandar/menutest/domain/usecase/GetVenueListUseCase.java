package com.aleksandar.menutest.domain.usecase;

import com.aleksandar.menutest.domain.repository.VenueRepository;

public class GetVenueListUseCase {
    public GetVenueListUseCase(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    private VenueRepository venueRepository;
}
