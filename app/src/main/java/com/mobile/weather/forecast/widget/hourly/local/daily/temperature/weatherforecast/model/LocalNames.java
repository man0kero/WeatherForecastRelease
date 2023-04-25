package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model;

import java.io.Serializable;

public class LocalNames implements Serializable {
    private String ascii;
    private String feature_name;
    private String en;
    private String ru;
    private String uk;

    public String getAscii() {
        return ascii;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public String getEn() {
        return en;
    }

    public String getRu() {
        return ru;
    }

    public String getUk() {
        return uk;
    }
}