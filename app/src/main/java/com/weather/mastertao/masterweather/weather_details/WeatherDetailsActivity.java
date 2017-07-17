package com.weather.mastertao.masterweather.weather_details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.WeatherApplication;
import com.weather.mastertao.masterweather.domain.DarkSkyForecastResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailsActivity extends AppCompatActivity implements WeatherDetailsContract.WeatherDetailView{

    @Inject
    WeatherDetailsPresenter weatherDetailsPresenter;

    @InjectExtra("lat")
    double lat;
    @InjectExtra("lng")
    double lng;

    @BindView(R.id.summary)
    TextView summaryView;

    @BindView(R.id.temperature)
    TextView temperatureView;

    @BindView(R.id.windSpeedPressure)
    TextView windSpeedPressureView;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_detail);
        Dart.inject(this);
        ButterKnife.bind(this);
        ((WeatherApplication) getApplication()).getApplicationComponent().inject(this);

        weatherDetailsPresenter.subscribe(this);
    }

    @Override public void onResume(){
        super.onResume();
        weatherDetailsPresenter.loadWeatherInformation(lat, lng);
    }

    @Override public void onPause(){
        super.onPause();
        weatherDetailsPresenter.unsubscribe();
    }

    @Override
    public void showWeatherInformation(DarkSkyForecastResponse forecastResponse) {
        summaryView.setText(forecastResponse.currently.summary);
        temperatureView.setText(forecastResponse.currently.temperature + "");
        windSpeedPressureView.setText(forecastResponse.currently.windSpeed + " " + forecastResponse.currently.pressure);
    }
}
