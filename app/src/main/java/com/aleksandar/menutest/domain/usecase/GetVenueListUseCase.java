package com.aleksandar.menutest.domain.usecase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.domain.repository.VenueRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetVenueListUseCase {
    private VenueRepository venueRepository;

    public GetVenueListUseCase(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Resource<VenueListApiResponse>> venueListAPiResponseLiveData = new MutableLiveData<>();

    public void getVenueList(String latitude, String longitude) {
        venueListAPiResponseLiveData.postValue(Resource.loading());
        Single<VenueListApiResponse> venueListSingleResponse = this.venueRepository.getVenueList(latitude, longitude);
        compositeDisposable.add(venueListSingleResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<VenueListApiResponse>() {
                    @Override
                    public void onSuccess(@NonNull VenueListApiResponse venueListApiResponse) {
                        venueListAPiResponseLiveData.postValue(Resource.success(venueListApiResponse));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        venueListAPiResponseLiveData.postValue(Resource.error(e));
                    }
                }));
    }

    public LiveData<Resource<VenueListApiResponse>> getVenueAPiResponseLiveData() {
        return venueListAPiResponseLiveData;
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }
}
