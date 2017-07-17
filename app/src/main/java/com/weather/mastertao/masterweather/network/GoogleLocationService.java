package com.weather.mastertao.masterweather.network;

import com.weather.mastertao.masterweather.domain.GoogleAutoCompleteLocation;
import com.weather.mastertao.masterweather.domain.GoogleLocationDetails;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleLocationService {
    @GET("maps/api/place/autocomplete/json")
    Maybe<GoogleAutoCompleteLocation> getLocationAutocomplete(@Query("key") String key, @Query("input") String locationName, @Query("types") String types);

    @GET("maps/api/place/details/json")
    Maybe<GoogleLocationDetails> getLocationDetails(@Query("key") String key, @Query("placeid") String placeId);
}
