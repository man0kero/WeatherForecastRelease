package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.api.ApiProvider;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.GeoCodeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityRepository {
    private final String API_KEY_WEATHER;
    private final Application application;
    private final MutableLiveData<List<GeoCodeModel>> mutableLiveData = new MutableLiveData<>();

    public CityRepository(Application application) {
        this.application = application;
        API_KEY_WEATHER = application.getString(R.string.weather_api_key);
    }

    public MutableLiveData<List<GeoCodeModel>> getMutableLiveData(String city) {
        ApiProvider.provideGeoCodingApi().getCityByName(city, "10", API_KEY_WEATHER)
                .enqueue(new Callback<List<GeoCodeModel>>() {
                    @Override
                    public void onResponse(Call<List<GeoCodeModel>> call, Response<List<GeoCodeModel>> response) {
                        if (response.body().size() == 0) {
                            Toast.makeText(application,
                                    application.getApplicationContext().getText(R.string.onResponse_toast),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GeoCodeModel>> call, Throwable t) {
                        Log.d("FailThrowable", "Fail: " + t.getMessage());
                        Toast.makeText(application,
                                application.getApplicationContext().getText(R.string.onFailure_toast),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return mutableLiveData;
    }
}
