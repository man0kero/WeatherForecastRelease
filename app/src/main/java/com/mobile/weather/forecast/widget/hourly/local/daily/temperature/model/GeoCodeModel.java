package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model;

import java.io.Serializable;

public class GeoCodeModel implements Serializable {
    private String country;
    private LocalNames local_names;
    private double lat;
    private double lon;
    private String name;
    private String state;

    public String getCountry() {
        return country;
    }

    public LocalNames getLocal_names() {
        return local_names;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}


