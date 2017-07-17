package com.weather.mastertao.masterweather.domain;

import com.google.gson.annotations.SerializedName;

public class Predictions {

    @SerializedName("place_id")
    public String id;

    public String description;
}
