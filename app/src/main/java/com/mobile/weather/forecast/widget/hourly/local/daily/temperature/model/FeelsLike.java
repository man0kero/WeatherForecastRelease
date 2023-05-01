package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model;

import java.io.Serializable;

public class FeelsLike implements Serializable {
    private double day;
    private double eve;
    private double morn;
    private double night;

    public double getDay() {
        return day;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }

    public double getNight() {
        return night;
    }
}
