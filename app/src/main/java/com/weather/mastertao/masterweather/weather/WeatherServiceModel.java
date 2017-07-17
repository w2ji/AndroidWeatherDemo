package com.weather.mastertao.masterweather.weather;

import com.weather.mastertao.masterweather.domain.GoogleAutoCompleteLocation;
import com.weather.mastertao.masterweather.domain.GoogleLocationDetails;
import com.weather.mastertao.masterweather.network.GoogleLocationService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;

public class WeatherServiceModel {

    @Inject
    GoogleLocationService googleLocationService;

    @Inject
    @Named("google.location.api.key")
    String apiKey;


    public GoogleAutoCompleteLocation location;

    public Map<String, GoogleAutoCompleteLocation> cache;

    @Inject
    public WeatherServiceModel() {
        cache = new HashMap<>();
    }

    public GoogleAutoCompleteLocation getLastLocationSearchResult() {
        return location;
    }

    public Maybe<GoogleAutoCompleteLocation> getLocationAutocomplete(String text) {
        return Maybe.concat(Maybe.fromCallable(() -> cache.get(text)), googleLocationService
                .getLocationAutocomplete(apiKey, text, "geocode")
                .doOnSuccess(loc -> {
                    this.location = loc;
                    this.cache.put(text, loc);
                }))
                .firstElement();
    }

    public Maybe<GoogleLocationDetails> getLocationDetails(String id) {
        return googleLocationService
                .getLocationDetails(apiKey, id);
    }
}
