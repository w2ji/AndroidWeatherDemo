package com.weather.mastertao.masterweather.images;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemCreator;
import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.WeatherApplication;
import com.weather.mastertao.masterweather.domain.Pagination;
import com.weather.mastertao.masterweather.domain.TenorImageItem;
import com.weather.mastertao.masterweather.recycler_view.UniversalRecyclerViewAdapter;
import com.weather.mastertao.masterweather.recycler_view.mappings.PaginationMapping;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicturesListActivity extends AppCompatActivity implements PictureListContract.View {

    PictureListPresenter presenter;

    @BindView(R.id.photoList)
    protected RecyclerView recyclerView;

    protected PictureAdapter pictureAdapter;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.tenor_list_image);
        ButterKnife.bind(this);
        WeatherApplication.getApplicationComponent(getApplication()).inject(this);


        PictureListPresenter temp = (PictureListPresenter) getLastCustomNonConfigurationInstance();
        if (temp != null) {
            this.presenter = temp;
        } else {
            this.presenter = new PictureListPresenter();
            ((WeatherApplication) getApplication()).getApplicationComponent().inject(this.presenter);
        }

        setupRecyclerView();
    }

    @Override
    public void onStart(){
        super.onStart();
        this.presenter.subscribe(this);
    }

    @Override
    public void onStop(){
        this.presenter.unsubscribe();
        super.onStop();
    }

    private void setupRecyclerView(){
        pictureAdapter = new PictureAdapter();
        recyclerView.setAdapter(pictureAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Paginate.Callbacks callbacks = new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                presenter.loadMoreImages();
            }

            @Override
            public boolean isLoading() {
                // Indicate whether new page loading is in progress or not
                return presenter.isLoading();
            }

            @Override
            public boolean hasLoadedAllItems() {
                // Indicate whether all data (pages) are loaded or not
                return presenter.isLastPage();
            }
        };
        Paginate.with(recyclerView, callbacks)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
                .build();
    }

    private class CustomLoadingListItemCreator implements LoadingListItemCreator {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.pagination, parent, false);
            return new PaginationMapping.PaginationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // Bind custom loading row if needed
        }
    }

    @Override
    public PictureListPresenter onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void onImagesLoaded(List<TenorImageItem> images) {
        pictureAdapter.addImages(images);
    }

    @Override
    public void onNoMoreDataLoad() {

    }
}
