package com.weather.mastertao.masterweather.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.weather.mastertao.masterweather.R;
import com.weather.mastertao.masterweather.WeatherApplication;
import com.weather.mastertao.masterweather.domain.GoogleLocationDetails;
import com.weather.mastertao.masterweather.domain.Predictions;
import com.weather.mastertao.masterweather.images.PicturesListActivity;
import com.weather.mastertao.masterweather.images.PicturesListActivityV2;
import com.weather.mastertao.masterweather.network.DarkSkyService;
import com.weather.mastertao.masterweather.weather_details.WeatherDetailsActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class LocationListActivity extends AppCompatActivity implements WeatherContract.WeatherContractView {

    @Inject
    DarkSkyService darkSkyService;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.editText)
    EditText editText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    @Named("dark.sky.api.key")
    String apiKey;

    @Inject
    CompositeDisposable compositeDisposable;

    private ListLocationAdapter adapter;

    WeatherPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((WeatherApplication) getApplication()).getApplicationComponent().inject(this);

        compositeDisposable = new CompositeDisposable();

        WeatherPresenter temp = (WeatherPresenter) getLastCustomNonConfigurationInstance();
        if (temp != null) {
            this.presenter = temp;
        } else {
            this.presenter = new WeatherPresenter();
            ((WeatherApplication) getApplication()).getApplicationComponent().inject(this.presenter);
        }

        setupToolBar();
        setupRecyclerView();

        RxTextView.textChanges(editText).debounce(500, TimeUnit.MILLISECONDS).subscribe(c -> presenter.locationSearch(c.toString()));

        presenter.subscribe(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more_tab_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.photos) {
            Intent intent = new Intent(this, PicturesListActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.photos_v2) {
            Intent intent = new Intent(this, PicturesListActivityV2.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public WeatherPresenter onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        presenter.unsubscribe();
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        adapter = new ListLocationAdapter(presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showLocationList(List<Predictions> locationList) {
        adapter.setLocationList(locationList);
    }

    @Override
    public void startNewActivity(GoogleLocationDetails locationDetails) {
        double lat = locationDetails.result.geometry.location.lat;
        double lng = locationDetails.result.geometry.location.lng;

        Toast.makeText(getApplicationContext(), lat + "," + lng, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        startActivity(intent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
