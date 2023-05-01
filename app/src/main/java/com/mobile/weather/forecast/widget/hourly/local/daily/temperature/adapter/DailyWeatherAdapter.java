package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.adapter;

import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.DAY_WEEK_NAME_LONG;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.provideIcon;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toDateFormatOf;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toDegree;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toPercentString;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.databinding.ItemDailyWeatherBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.DailyWeatherModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.view.DailyWeatherActivity;

import java.util.List;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ItemViewHolder> {

    private final Context context;
    private final List<DailyWeatherModel> result;

    public DailyWeatherAdapter(Context context, List<DailyWeatherModel> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDailyWeatherBinding binding = ItemDailyWeatherBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.binding.itemDailyIcon.setImageResource(
                provideIcon(result.get(position).getWeather().get(0).getIcon()));
        holder.binding.itemDailyMinTempTv.setText(
                toDegree(result.get(position).getTemp().getMin())
        );
        holder.binding.itemDailyMaxTempTv.setText(
                toDegree(result.get(position).getTemp().getMax())
        );

        if (result.get(position).getPop() < 0.01) {
            holder.binding.itemDailyPercent.setVisibility(View.INVISIBLE);
        } else {
            holder.binding.itemDailyPercent.setText(
                    toPercentString(result.get(position).getPop()));
        }

        String dataOfDay = toDateFormatOf(result.get(position).getDt(), DAY_WEEK_NAME_LONG);
        if (dataOfDay.startsWith("0")) {
            dataOfDay = dataOfDay.substring(1);
            holder.binding.itemDailyDateTv.setText(dataOfDay);
        } else holder.binding.itemDailyDateTv.setText(dataOfDay);


        holder.binding.dayContainer.setOnClickListener(view -> {
            ((DailyWeatherActivity) context).fillDayInfo(position);
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemDailyWeatherBinding binding;

        public ItemViewHolder(@NonNull ItemDailyWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
