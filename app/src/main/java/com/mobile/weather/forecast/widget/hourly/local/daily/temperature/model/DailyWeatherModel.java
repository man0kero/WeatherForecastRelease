package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model;

import java.io.Serializable;
import java.util.List;

public class DailyWeatherModel implements Serializable {
    private int clouds;
    private double dew_point;
    private long dt;
    private FeelsLike feels_like;
    private int humidity;
    private double moon_phase;
    private int moonrise;
    private int moonset;
    private double pop;
    private int pressure;
    private long sunrise;
    private long sunset;
    private Temp temp;
    private double uvi;
    private List<Weather> weather;
    private int wind_deg;
    private double wind_gust;
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

    public FeelsLike getFeels_like() {
        return feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getMoon_phase() {
        return moon_phase;
    }

    public int getMoonrise() {
        return moonrise;
    }

    public int getMoonset() {
        return moonset;
    }

    public double getPop() {
        return pop;
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

    public Temp getTemp() {
        return temp;
    }

    public double getUvi() {
        return uvi;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public double getWind_gust() {
        return wind_gust;
    }

    public double getWind_speed() {
        return wind_speed;
    }
}
