package com.weather.mastertao.masterweather.domain;

import java.util.ArrayList;
import java.util.List;

public class TenorImageResponse {

    public List<Result> results = new ArrayList<>();
    public String next;

    public static class Result {
        public String url;
        public List<Media> media;
    }

    public static class Media {
        public GIF gif;
    }

    public static class GIF {
        public String preview;
    }
}
