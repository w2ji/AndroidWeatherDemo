package com.weather.mastertao.masterweather.module;

import android.app.Application;

import com.weather.mastertao.masterweather.images.PictureListPresenter;
import com.weather.mastertao.masterweather.images.PicturesListActivity;
import com.weather.mastertao.masterweather.images.PicturesListActivityV2;
import com.weather.mastertao.masterweather.network.DarkSkyService;
import com.weather.mastertao.masterweather.network.GoogleLocationService;
import com.weather.mastertao.masterweather.weather.LocationListActivity;
import com.weather.mastertao.masterweather.weather.WeatherPresenter;
import com.weather.mastertao.masterweather.weather_details.WeatherDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(LocationListActivity activity);
    void inject(PicturesListActivityV2 activity);
    void inject(WeatherDetailsActivity activity);
    void inject(PicturesListActivity activity);
    void inject(WeatherPresenter presenter);
    void inject(PictureListPresenter presenter);

    GoogleLocationService googleLocationClient();
    DarkSkyService darkSkyClient();
}
