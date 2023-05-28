package com.aleksandar.menutest.presentation.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.usecase.AuthUseCase;
import com.aleksandar.menutest.domain.usecase.GetVenueListUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VenueViewModelTest {
    private static final String LONGITUDE = "10.1234";
    private static final String LATITUDE = "21.26907";

    @Mock
    private Application mockApplication;

    @Mock
    private AuthUseCase mockAuthUseCase;

    @Mock
    private GetVenueListUseCase mockVenueListUseCase;

    private VenueViewModel venueViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        venueViewModel = new VenueViewModel(mockApplication, mockAuthUseCase, mockVenueListUseCase);
    }

    @Test
    public void testLogOut() {
        venueViewModel.logOut();
        verify(mockAuthUseCase).logOut();
    }

    @Test
    public void testGetVenueList() {
        venueViewModel.getVenueList(LATITUDE, LONGITUDE);
        verify(mockVenueListUseCase).getVenueList(LATITUDE, LONGITUDE);
    }

    @Test
    public void testGetVenueApiResponseLiveData() {
        LiveData<Resource<VenueListApiResponse>> mockLiveData = mock(LiveData.class);
        when(mockVenueListUseCase.getVenueAPiResponseLiveData()).thenReturn(mockLiveData);
        LiveData<Resource<VenueListApiResponse>> result = venueViewModel.getVenueAPiResponseLiveData();
        assertEquals(mockLiveData, result);
    }

    @Test
    public void testClearDisposable() {
        venueViewModel.clearDisposable();
        verify(mockAuthUseCase).clearDisposable();
        verify(mockVenueListUseCase).clearDisposable();
    }
}
