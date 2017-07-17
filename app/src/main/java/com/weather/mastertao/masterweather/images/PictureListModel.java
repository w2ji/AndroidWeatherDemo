package com.weather.mastertao.masterweather.images;

import com.weather.mastertao.masterweather.domain.TenorImageItem;
import com.weather.mastertao.masterweather.domain.TenorImageResponse;
import com.weather.mastertao.masterweather.network.TenorImageService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class PictureListModel {


    @Inject
    TenorImageService tenorImageService;

    @Inject
    @Named("tenor.api.key")
    String apiKey;

    private String offset;
    private int limit;

    @Inject
    public PictureListModel() {
        offset = null;
        limit = 30;
    }

    public Single<List<TenorImageItem>> loadMoreImages() {
        return tenorImageService
                .getImages(apiKey, limit, offset)
                .map(tenorImageResponse -> {
                    offset = tenorImageResponse.next;
                    return mapList(tenorImageResponse);
                });
    }

    private List<TenorImageItem> mapList(TenorImageResponse response) {
        List<TenorImageItem> items = new ArrayList<>();
        for (TenorImageResponse.Result result : response.results) {
            TenorImageItem item = new TenorImageItem();
            item.url = result.media.get(0).gif.preview;
            items.add(item);
        }

        return items;
    }
}
