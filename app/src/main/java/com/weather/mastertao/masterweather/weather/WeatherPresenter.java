package com.weather.mastertao.masterweather.weather;

import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter implements WeatherContract.WeatherContractPresenter {

    private WeatherContract.WeatherContractView view;

    @VisibleForTesting
    CompositeDisposable compositeDisposable;

    @Inject
    WeatherServiceModel weatherServiceModel;

    @Inject
    public WeatherPresenter() {
    }

    @Override
    public void subscribe(WeatherContract.WeatherContractView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
        if (weatherServiceModel.getLastLocationSearchResult() != null){
            view.showLocationList(weatherServiceModel.getLastLocationSearchResult().predictions);
        }
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
        view = null;
    }

    @Override
    public void locationClicked(String id) {
        compositeDisposable.add(
                        weatherServiceModel.getLocationDetails(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> view.startNewActivity(data)));
    }

    @Override
    public void locationSearch(String text) {
        compositeDisposable.add(
                        weatherServiceModel.getLocationAutocomplete(text)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(googleAutoCompleteLocation -> {
                                    view.showLocationList(googleAutoCompleteLocation.predictions);
                                },
                                error -> view.showError(error.getMessage())));
    }
}
