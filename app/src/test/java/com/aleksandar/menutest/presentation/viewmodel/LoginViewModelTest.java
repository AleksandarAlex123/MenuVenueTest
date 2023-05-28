package com.aleksandar.menutest.presentation.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.usecase.AuthUseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginViewModelTest {
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "test1234";

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AuthUseCase authUseCase;

    private LoginViewModel loginViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Application mockApplication = Mockito.mock(Application.class);
        loginViewModel = new LoginViewModel(mockApplication, authUseCase);
    }

    @Test
    public void testLogin() {
        loginViewModel.login(EMAIL, PASSWORD);
        verify(authUseCase).login(EMAIL, PASSWORD);
    }

    @Test
    public void testIsUserLoggedIn() {
        boolean expected = true;
        when(authUseCase.isUserLoggedIn()).thenReturn(expected);
        boolean result = loginViewModel.isUserLoggedIn();
        assertEquals(expected, result);
    }

    @Test
    public void testGetLoginApiResponseLiveData() {
        LiveData<Resource<LoginAPiResponse>> mockLiveData = Mockito.mock(LiveData.class);
        when(authUseCase.getLoginAPiResponseLiveData()).thenReturn(mockLiveData);
        LiveData<Resource<LoginAPiResponse>> resultLiveData = loginViewModel.getLoginAPiResponseLiveData();
        assertEquals(mockLiveData, resultLiveData);
    }

    @Test
    public void testClearDisposable() {
        loginViewModel.clearDisposable();
        verify(authUseCase).clearDisposable();
    }
}
