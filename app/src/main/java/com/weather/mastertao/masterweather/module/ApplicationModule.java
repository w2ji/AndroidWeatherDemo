package com.weather.mastertao.masterweather.module;

import android.app.Application;

import com.weather.mastertao.masterweather.network.DarkSkyService;
import com.weather.mastertao.masterweather.network.GoogleLocationService;
import com.weather.mastertao.masterweather.network.TenorImageService;
import com.weather.mastertao.masterweather.weather.WeatherPresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    @Provides
    @Named("dark.sky.api.key")
    String darkSkyApiKey(){
        return "4405046dd6373cb16f82951be4068efe";
    }

    @Provides
    @Singleton
    DarkSkyService provideDarkSkyService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.darksky.net/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(DarkSkyService.class);
    }

    @Provides
    @Named("google.location.api.key")
    String googleLocationKey(){
        return "AIzaSyADbqztNCoNtOS0xSSKlm3EViyhEXiBIfA";
    }

    @Provides
    @Singleton
    GoogleLocationService provideGoogleLocationService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GoogleLocationService.class);
    }

    @Provides
    @Named("tenor.api.key")
    String tenorApiKey(){
        return "LIVDSRZULELA";
    }

    @Provides
    @Singleton
    TenorImageService provideTenorImageService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.tenor.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TenorImageService.class);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }
}
