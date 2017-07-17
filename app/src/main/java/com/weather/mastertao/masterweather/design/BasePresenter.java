package com.weather.mastertao.masterweather.design;

public interface BasePresenter<T> {
    void subscribe(T view);
    void unsubscribe();
}
