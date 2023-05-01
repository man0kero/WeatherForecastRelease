package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model;

import java.io.Serializable;

public class Weather implements Serializable {
    private String description;
    private String icon;
    private int id;
    private String main;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}
