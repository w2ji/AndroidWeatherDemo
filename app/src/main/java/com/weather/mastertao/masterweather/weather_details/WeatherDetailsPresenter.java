package com.weather.mastertao.masterweather.weather_details;

import com.weather.mastertao.masterweather.network.DarkSkyService;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherDetailsPresenter implements WeatherDetailsContract.WeatherDetailPresenter {

    @Inject
    DarkSkyService darkSkyService;

    @Inject
    @Named("dark.sky.api.key")
    String darkSkyApiKey;

    WeatherDetailsContract.WeatherDetailView weatherDetailView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public WeatherDetailsPresenter() {
    }


    @Override
    public void subscribe(WeatherDetailsContract.WeatherDetailView view) {
        weatherDetailView = view;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    @Override
    public void loadWeatherInformation(double lat, double lng) {
        compositeDisposable.add(darkSkyService
                .fetchWeatherInformation(darkSkyApiKey, lat + "", lng + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(r -> weatherDetailView.showWeatherInformation(r)));
    }
}
