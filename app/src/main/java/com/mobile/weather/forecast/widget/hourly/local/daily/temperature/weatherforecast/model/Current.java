package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model;

import java.io.Serializable;
import java.util.List;

public class Current implements Serializable {
    private int clouds;
    private double dew_point;
    private long dt;
    private double feels_like;
    private int humidity;
    private int pressure;
    private long sunrise;
    private long sunset;
    private double temp;
    private double uvi;
    private int visibility;
    private List<Weather> weather;
    private int wind_deg;
    private double wind_speed;

    public int getClouds() {
        return clouds;
    }

    public double getDew_point() {
        return dew_point;
    }

    public long getDt() {
        return dt;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public double getTemp() {
        return temp;
    }

    public double getUvi() {
        return uvi;
    }

    public int getVisibility() {
        return visibility;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public double getWind_speed() {
        return wind_speed;
    }
}
