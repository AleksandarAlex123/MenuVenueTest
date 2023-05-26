package com.aleksandar.menutest.domain.usecase;

import com.aleksandar.menutest.domain.repository.VenueRepository;

public class LoginUseCase {
    private VenueRepository venueRepository;

    public LoginUseCase(VenueRepository loginRepository) {
        this.venueRepository = venueRepository;
    }
}
