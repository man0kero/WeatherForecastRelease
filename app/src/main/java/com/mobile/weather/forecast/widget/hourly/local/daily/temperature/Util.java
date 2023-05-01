package com.mobile.weather.forecast.widget.hourly.local.daily.temperature;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Util {
    public static final String DAY_FULL_MONTH_NAME = "dd MMMM";
    public static final String HOUR_DOUBLE_DOT_MINUTE = "HH:mm";
    public static final String DAY_WEEK_NAME_LONG = "dd EEEE";


    public static int provideIcon(String iconCode) {
        switch (iconCode) {
            case "01n":
                return R.drawable.ic_01n;
            case "01d":
                return R.drawable.ic_01d;
            case "02n":
                return R.drawable.ic_02n;
            case "02d":
                return R.drawable.ic_02d;
            case "03d":
            case "03n":
            case "04d":
            case "04n":
                return R.drawable.ic_03d;
            case "09n":
            case "10n":
            case "09d":
            case "10d":
                return R.drawable.ic_09d;
            case "11n":
            case "11d":
                return R.drawable.ic_11d;
            case "13n":
            case "13d":
                return R.drawable.ic_13d;
            case "50n":
            case "50d":
                return R.drawable.ic_50d;
            default:
                return R.drawable.ic_error;
        }
    }

    public static String toDateFormatOf(long time, String format) {
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date(time * 1000));
    }

    public static String toDateFormatWid(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date(time);
        return formatter.format(date);
    }

    public static String toPercentString(double value) {
        int roundedValue = (int) Math.round(value * 100);
        return roundedValue + "%";
    }

    public static String toPercentStringWO(int value) {
        return value + "%";
    }

    public static String toDegree(double result) {
        return (int) (result - 273.15) + "°";
    }

    public static String toPressure(double input, Context context) {
        return (int) input + " " + context.getApplicationContext().getString(R.string.pressure);
    }

    public static String toWindSpeed(double input, Context context) {
        return (int) input + " " + context.getApplicationContext().getString(R.string.wind);
    }
}
