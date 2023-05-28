package com.aleksandar.menutest.data.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.repository.dataSource.RemoteDataSource;
import com.aleksandar.menutest.data.util.AppConstant;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

public class VenueRepositoryImplTest {

    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "test1234";


    @Mock
    private LocalDataSource mockLocalDataSource;

    @Mock
    private RemoteDataSource mockRemoteDataSource;

    private VenueRepositoryImpl venueRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        venueRepository = new VenueRepositoryImpl(mockLocalDataSource, mockRemoteDataSource);
    }

    @Test
    public void testGetVenueList() {
        String latitude = "123.456";
        String longitude = "789.012";
        VenueListApiResponse mockApiResponse = new VenueListApiResponse();
        when(mockRemoteDataSource.getVenueList(latitude, longitude)).thenReturn(Single.just(mockApiResponse));
        Single<VenueListApiResponse> result = venueRepository.getVenueList(latitude, longitude);
        verify(mockRemoteDataSource).getVenueList(latitude, longitude);
        verifyNoMoreInteractions(mockRemoteDataSource);
        result.test().assertValue(mockApiResponse);
    }

    @Test
    public void testLogin() {
        LoginAPiResponse mockApiResponse = new LoginAPiResponse();
        when(mockRemoteDataSource.login(EMAIL, PASSWORD)).thenReturn(Single.just(mockApiResponse));
        Single<LoginAPiResponse> result = venueRepository.login(EMAIL, PASSWORD);
        verify(mockRemoteDataSource).login(EMAIL, PASSWORD);
        verifyNoMoreInteractions(mockRemoteDataSource);
        result.test().assertValue(mockApiResponse);
    }

    @Test
    public void testSaveAccessToken() {
        String accessToken = "1234567890";
        venueRepository.saveAccessToken(accessToken);
        verify(mockLocalDataSource).saveData(AppConstant.ACCESS_TOKEN_KEY, accessToken);
        verifyNoMoreInteractions(mockLocalDataSource);
    }

    @Test
    public void testLogOut() {
        venueRepository.logOut();
        verify(mockLocalDataSource).clearData(AppConstant.ACCESS_TOKEN_KEY);
        verifyNoMoreInteractions(mockLocalDataSource);
    }

    @Test
    public void testIsUserLoggedIn() {
        when(mockLocalDataSource.isUserLogedIn()).thenReturn(true);
        boolean result = venueRepository.isUserLoggedIn();
        verify(mockLocalDataSource).isUserLogedIn();
        verifyNoMoreInteractions(mockLocalDataSource);
        assert (result);
    }
}
