package com.weather.mastertao.masterweather.domain;

public class DarkSkyForecastResponse {
    public double latitude;
    public double longitude;
    public Currently currently;

    public class Currently {
        public String summary;
        public double temperature;
        public double windSpeed;
        public double pressure;
    }
}
