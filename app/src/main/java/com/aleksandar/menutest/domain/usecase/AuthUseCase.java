package com.aleksandar.menutest.domain.usecase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aleksandar.menutest.data.model.LoginAPiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.repository.VenueRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class AuthUseCase {
    private VenueRepository venueRepository;

    public AuthUseCase(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Resource<LoginAPiResponse>> loginAPiResponseLiveData = new MutableLiveData<>();

    public void login(String email, String password) {
        loginAPiResponseLiveData.postValue(Resource.loading());
        Single<LoginAPiResponse> loginSingleResponse = this.venueRepository.login(email, password);
        compositeDisposable.add(loginSingleResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginAPiResponse>() {
                    @Override
                    public void onSuccess(@NonNull LoginAPiResponse loginAPiResponse) {
                        venueRepository.saveAccessToken(loginAPiResponse.getAccessToken());
                        loginAPiResponseLiveData.postValue(Resource.success(loginAPiResponse));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //This need to be removed !!!!!
                        venueRepository.saveAccessToken("mock_access_token");

                        loginAPiResponseLiveData.postValue(Resource.error(e));
                    }
                }));
    }

    public void logOut() {
        venueRepository.logOut();
    }

    public boolean isUserLoggedIn() {
        return venueRepository.isUserLoggedIn();
    }

    public LiveData<Resource<LoginAPiResponse>> getLoginAPiResponseLiveData() {
        return loginAPiResponseLiveData;
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }
}
