package com.weather.mastertao.masterweather.domain;

public class GoogleLocationDetails {

    public Result result;

    public class Result {
        public Geometry geometry;
    }

    public class Geometry {
        public Location location;
    }

    public class Location {
        public double lat = 0;
        public double lng = 0;
    }
}
