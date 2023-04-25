package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.adapter;


import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.HOUR_DOUBLE_DOT_MINUTE;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.provideIcon;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.toDateFormatOf;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.toDegree;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.toPercentString;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.databinding.ItemHourlyWeatherBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.HourlyWeatherModel;

import java.util.List;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ItemViewHolder> {

    private final Context context;
    private final List<HourlyWeatherModel> result;

    public HourlyWeatherAdapter(Context context, List<HourlyWeatherModel> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHourlyWeatherBinding binding = ItemHourlyWeatherBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.binding.itemHourlyTime.setText(
                toDateFormatOf(result.get(position).getDt(), HOUR_DOUBLE_DOT_MINUTE)
        );
        holder.binding.itemHourlyWeatherIcon.setImageResource(
                provideIcon(result.get(position).getWeather().get(0).getIcon())
        );
        holder.binding.itemHourlyTemp.setText(
                toDegree(result.get(position).getTemp())
        );
        holder.binding.itemHourlyProbability.setText(
                toPercentString(result.get(position).getPop())
        );
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemHourlyWeatherBinding binding;

        public ItemViewHolder(@NonNull ItemHourlyWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
