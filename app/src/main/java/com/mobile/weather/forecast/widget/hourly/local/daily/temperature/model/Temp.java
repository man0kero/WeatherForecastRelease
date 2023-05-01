package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model;

import java.io.Serializable;

public class Temp implements Serializable {
    private double day;
    private double eve;
    private double max;
    private double min;
    private double morn;
    private double night;

    public double getDay() {
        return day;
    }

    public double getEve() {
        return eve;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getMorn() {
        return morn;
    }

    public double getNight() {
        return night;
    }

    public double getAverage() {
        return ((day + night + eve + morn) / 4);
    }
}
