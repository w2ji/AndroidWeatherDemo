package com.weather.mastertao.masterweather.weather;

import com.weather.mastertao.masterweather.domain.GoogleAutoCompleteLocation;
import com.weather.mastertao.masterweather.images.PictureListContract;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Spy
    private WeatherPresenter presenter;

    @Mock
    private WeatherServiceModel weatherServiceModel;

    private WeatherContract.WeatherContractView weatherContractView;

    @Before
    public void setupMocksAndView() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.

        MockitoAnnotations.initMocks(this);

        presenter.weatherServiceModel = weatherServiceModel;
        presenter.weatherServiceModel.apiKey = "abcd";
        presenter.weatherServiceModel.location = mock(GoogleAutoCompleteLocation.class);
    }

    @Test
    public void testSubscribe() {
        // Get a reference to the class under test
        //presenter.subscribe(Mockito.mock(PictureListContract.View.class));

        // Then the presenter is set to the view
        assertNotNull(presenter.compositeDisposable);

        verify(presenter.weatherServiceModel, Mockito.atLeastOnce()).getLastLocationSearchResult();
    }

    @Test
    public void testPresenterLocationSearch() {
        // Get a reference to the class under test
        presenter.locationSearch("san");

        verify(presenter.weatherServiceModel).getLocationAutocomplete("san");
    }

}
