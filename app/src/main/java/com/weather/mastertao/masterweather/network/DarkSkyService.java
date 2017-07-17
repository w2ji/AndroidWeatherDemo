package com.weather.mastertao.masterweather.network;

import com.weather.mastertao.masterweather.domain.DarkSkyForecastResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyService {
    @GET("forecast/{key}/{latitude},{longitude}")
    Single<DarkSkyForecastResponse> fetchWeatherInformation(@Path("key") String key, @Path("latitude") String latitude, @Path("longitude") String longitude);
}
