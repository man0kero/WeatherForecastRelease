package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model;

import java.io.Serializable;
import java.util.List;

public class WeatherDataModel implements Serializable {
    private Current current;
    private List<DailyWeatherModel> daily;
    private List<HourlyWeatherModel> hourly;
    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;

    public Current getCurrent() {
        return current;
    }

    public List<DailyWeatherModel> getDaily() {
        return daily;
    }

    public List<HourlyWeatherModel> getHourly() {
        return hourly;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }
}
