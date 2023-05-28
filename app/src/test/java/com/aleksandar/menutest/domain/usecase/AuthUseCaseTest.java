package com.aleksandar.menutest.domain.usecase;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.repository.VenueRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Build;
import android.os.Looper;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.M})
public class AuthUseCaseTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private VenueRepository mockVenueRepository;

    @Mock
    private Looper looperMock;

    @Mock
    private Observer<Resource<LoginAPiResponse>> mockApiResponseObserver;

    private AuthUseCase authUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        looperMock = mock(Looper.class);
        authUseCase = new AuthUseCase(mockVenueRepository);
        authUseCase.getLoginAPiResponseLiveData().observeForever(mockApiResponseObserver);
    }

    @Test
    public void login_withValidCredentials_success() {
        String email = "test@example.com";
        String password = "password";
        LoginAPiResponse loginResponse = new LoginAPiResponse();
        when(mockVenueRepository.login(email, password))
                .thenReturn(Single.just(loginResponse));
        authUseCase.login(email, password);
        verify(mockVenueRepository).saveAccessToken("access_token");
        verify(mockApiResponseObserver).onChanged(Resource.success(loginResponse));
    }

    @Test
    public void login_withInvalidCredentials_error() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        Throwable error = new Throwable("Invalid credentials");
        when(mockVenueRepository.login(email, password))
                .thenReturn(Single.error(error));
        authUseCase.login(email, password);
        verify(mockApiResponseObserver).onChanged(Resource.error(error));
    }

    @Test
    public void logOut_shouldCallRepository() {
        authUseCase.logOut();
        verify(mockVenueRepository).logOut();
    }

}
