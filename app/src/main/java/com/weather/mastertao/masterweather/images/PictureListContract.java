package com.weather.mastertao.masterweather.images;

import com.weather.mastertao.masterweather.design.BasePresenter;
import com.weather.mastertao.masterweather.domain.TenorImageItem;

import java.util.List;

public class PictureListContract {

    public interface View {
        void onImagesLoaded(List<TenorImageItem> images);
        void onNoMoreDataLoad();
    }

    public interface Presenter extends BasePresenter<View> {
        void loadMoreImages();
        Boolean isLastPage();
        Boolean isLoading();
    }
}
