package com.weather.mastertao.masterweather.weather_details;

import com.weather.mastertao.masterweather.design.BasePresenter;
import com.weather.mastertao.masterweather.domain.DarkSkyForecastResponse;

public interface WeatherDetailsContract {

    interface WeatherDetailView {
        void showWeatherInformation(DarkSkyForecastResponse forecastResponse);
    }

    interface WeatherDetailPresenter extends BasePresenter<WeatherDetailView> {
        void loadWeatherInformation(double lat, double lng);
    }
}
