package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.api.ApiProvider;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.WeatherDataModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.view.MainActivity;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final String API_KEY_WEATHER;
    private final Application application;
    private final MutableLiveData<WeatherDataModel> mutableLiveData = new MutableLiveData<>();
    private final String lang = Locale.getDefault().getLanguage();
    private int retryCount = 200;

    public MainRepository(Application application) {
        this.application = application;
        API_KEY_WEATHER = application.getString(R.string.weather_api_key);
    }

    public MutableLiveData<WeatherDataModel> getWeather(String lat, String lon) {
        ApiProvider.provideWeatherApi().getWeatherForecast(lat, lon,
                        "minutely,alerts", API_KEY_WEATHER, lang)
                .enqueue(new Callback<WeatherDataModel>() {
                    @Override
                    public void onResponse(Call<WeatherDataModel> call, Response<WeatherDataModel> response) {
                        mutableLiveData.setValue(response.body());
                        Toast.makeText(application.getApplicationContext(),
                                application.getApplicationContext().getText(R.string.onUpdate_toast),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<WeatherDataModel> call, Throwable t) {
                        if (retryCount > 0) {
                            getWeather(lat, lon);
                            retryCount = retryCount - 1;
                        } else {
                            Toast.makeText(application.getApplicationContext(),
                                    application.getApplicationContext().getText(R.string.onFailure_toast),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return mutableLiveData;
    }
}
