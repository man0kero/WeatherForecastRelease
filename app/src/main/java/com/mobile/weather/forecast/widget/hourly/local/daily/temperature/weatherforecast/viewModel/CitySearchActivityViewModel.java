package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.GeoCodeModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.repository.CityRepository;

import java.util.List;

public class CitySearchActivityViewModel extends AndroidViewModel {

    private final CityRepository cityRepository;

    public CitySearchActivityViewModel(@NonNull Application application) {
        super(application);
        cityRepository = new CityRepository(application);
    }

    public LiveData<List<GeoCodeModel>> getAllCities(String city) {
        return cityRepository.getMutableLiveData(city);
    }
}
