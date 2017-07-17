package com.weather.mastertao.masterweather.images;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.WeatherApplication;
import com.weather.mastertao.masterweather.domain.Pagination;
import com.weather.mastertao.masterweather.domain.TenorImageItem;
import com.weather.mastertao.masterweather.recycler_view.PaginationScrollListener;
import com.weather.mastertao.masterweather.recycler_view.UniversalRecyclerViewAdapter;
import com.weather.mastertao.masterweather.recycler_view.mappings.PaginationMapping;
import com.weather.mastertao.masterweather.recycler_view.mappings.PictureImageMapping;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicturesListActivityV2 extends AppCompatActivity implements PictureListContract.View {

    PictureListPresenter presenter;

    @BindView(R.id.photoList)
    protected RecyclerView recyclerView;

    @Inject
    UniversalRecyclerViewAdapter universalRecyclerViewAdapter;

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

        this.presenter.subscribe(this);
    }

    private void setupRecyclerView(){
        universalRecyclerViewAdapter
                .registerMappings(
                        new PictureImageMapping(TenorImageItem.class),
                        new PaginationMapping(Pagination.class).spanSize(2));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return universalRecyclerViewAdapter.getSpanSize(position);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(universalRecyclerViewAdapter);
        recyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
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
        });
    }

    @Override
    public PictureListPresenter onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void onImagesLoaded(List<TenorImageItem> images) {
        universalRecyclerViewAdapter.appendMoreElements(new ArrayList(images));
    }

    @Override
    public void onNoMoreDataLoad() {
        universalRecyclerViewAdapter.disableSpinner();
    }
}
