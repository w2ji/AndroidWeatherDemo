package com.weather.mastertao.masterweather.network;

import com.weather.mastertao.masterweather.domain.TenorImageResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TenorImageService {

    @GET("/v1/trending")
    Single<TenorImageResponse> getImages(@Query("key") String key, @Query("limit") int limit, @Query("pos") String pos);
}
