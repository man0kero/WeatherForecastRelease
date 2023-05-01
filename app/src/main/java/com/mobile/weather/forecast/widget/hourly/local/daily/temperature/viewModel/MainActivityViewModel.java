package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.WeatherDataModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.repository.MainRepository;


public class MainActivityViewModel extends AndroidViewModel {
    private final MainRepository mainRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mainRepository = new MainRepository(application);
    }

    public LiveData<WeatherDataModel> getWeather(String lat, String lon) {
        return mainRepository.getWeather(lat, lon);
    }
}
