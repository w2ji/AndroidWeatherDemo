package com.weather.mastertao.masterweather.images;

import android.support.v7.widget.GridLayoutManager;

import com.weather.mastertao.masterweather.recycler_view.PaginationScrollListener;

public class PicturePaginationScrollListener extends PaginationScrollListener {

    PictureListPresenter presenter;

    public PicturePaginationScrollListener(GridLayoutManager gridLayoutManager, PictureListPresenter pictureListPresenter) {
        super(gridLayoutManager);
        this.presenter = pictureListPresenter;
    }

    @Override
    protected void loadMoreItems() {
        presenter.loadMoreImages();
    }

    @Override
    public boolean isLastPage() {
        return presenter.isLastPage();
    }

    @Override
    public boolean isLoading() {
        return presenter.isLoading();
    }
}
