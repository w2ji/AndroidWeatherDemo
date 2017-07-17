package com.weather.mastertao.masterweather.weather;

import com.weather.mastertao.masterweather.design.BasePresenter;
import com.weather.mastertao.masterweather.domain.GoogleLocationDetails;
import com.weather.mastertao.masterweather.domain.Predictions;

import java.util.List;

public class WeatherContract {

    public interface WeatherContractView {
        void showLocationList(List<Predictions> locationList);

        void startNewActivity(GoogleLocationDetails locationDetails);

        void showError(String error);
    }

    public interface WeatherContractPresenter extends BasePresenter<WeatherContractView> {

        void locationClicked(String id);

        void locationSearch(String text);
    }
}
