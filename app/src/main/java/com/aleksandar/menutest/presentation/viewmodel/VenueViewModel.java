package com.aleksandar.menutest.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.usecase.AuthUseCase;
import com.aleksandar.menutest.domain.usecase.GetVenueListUseCase;

public class VenueViewModel extends AndroidViewModel {

    private AuthUseCase authUseCase;
    private GetVenueListUseCase venueListUseCase;

    public VenueViewModel(@NonNull Application application, AuthUseCase authUseCase, GetVenueListUseCase venueListUseCase) {
        super(application);
        this.authUseCase = authUseCase;
        this.venueListUseCase = venueListUseCase;
    }

    public void logOut() {
        authUseCase.logOut();
    }

    public void getVenueList(String latitude, String longitude) {
        venueListUseCase.getVenueList(latitude, longitude);
    }

    public LiveData<Resource<VenueListApiResponse>> getVenueAPiResponseLiveData() {
        return venueListUseCase.getVenueAPiResponseLiveData();
    }

    public void clearDisposable() {
        authUseCase.clearDisposable();
        venueListUseCase.clearDisposable();
    }
}
