package com.weather.mastertao.masterweather;

import android.app.Application;

import com.weather.mastertao.masterweather.module.ApplicationComponent;
import com.weather.mastertao.masterweather.module.ApplicationModule;
import com.weather.mastertao.masterweather.module.DaggerApplicationComponent;


public class WeatherApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();

    }

    public static ApplicationComponent getApplicationComponent(Application application) {
        return ((WeatherApplication) application).applicationComponent;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
