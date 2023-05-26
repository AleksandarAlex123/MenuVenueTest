package com.aleksandar.menutest.presentation.di;


import com.aleksandar.menutest.BuildConfig;
import com.aleksandar.menutest.data.api.VenueApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    VenueApiService provideVenueAPIService(Retrofit retrofit){
        return retrofit.create(VenueApiService.class);
    }
}
