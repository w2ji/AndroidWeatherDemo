package com.weather.mastertao.masterweather.images;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PictureListPresenter implements PictureListContract.Presenter {

    PictureListContract.View view;

    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    PictureListModel pictureListModel;

    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Inject
    public PictureListPresenter() {
    }

    @Override
    public void subscribe(PictureListContract.View view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        this.compositeDisposable.dispose();
    }

    @Override
    public void loadMoreImages() {
        isLoading = true;
        compositeDisposable.add(pictureListModel
                .loadMoreImages()
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess(images -> {
                    if (images.size() == 0){
                        isLastPage = true;
                        view.onNoMoreDataLoad();
                    }
                    isLoading = false;
                })
                .subscribe(images -> {
                    view.onImagesLoaded(images);
                }));
    }

    @Override
    public Boolean isLastPage() {
        return isLastPage;
    }

    @Override
    public Boolean isLoading() {
        return isLoading;
    }

}
