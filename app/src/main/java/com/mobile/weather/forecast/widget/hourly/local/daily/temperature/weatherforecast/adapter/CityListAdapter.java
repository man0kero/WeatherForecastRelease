package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.databinding.ItemCityResultBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.GeoCodeModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.view.MainActivity;

import java.util.ArrayList;
import java.util.Locale;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ItemViewHolder> {

    private final Context context;
    private final ArrayList<GeoCodeModel> results;

    public CityListAdapter(Context context, ArrayList<GeoCodeModel> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCityResultBinding binding = ItemCityResultBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.binding.itemCity.setText(setCity(position));
        setState(holder, position);
        setCountry(holder, position);
        setClickListener(holder, position);

    }

    private void setClickListener(ItemViewHolder holder, int position) {
        holder.binding.cityItem.setOnClickListener(view -> {
            SharedPreferences preferences = context.getSharedPreferences(
                    context.getString(R.string.prefs_name), MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(
                    context.getString(R.string.key_city_name), setCity(position));
            editor.putString(
                    context.getString(R.string.key_lat), String.valueOf(results.get(position).getLat()));
            editor.putString(
                    context.getString(R.string.key_lon), String.valueOf(results.get(position).getLon()));
            editor.apply();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        });
    }

    private void setCountry(ItemViewHolder holder, int position) {
        holder.binding.itemCountry.setText(
                new Locale("", results.get(position)
                        .getCountry()).getDisplayName());
    }

    private void setState(ItemViewHolder holder, int position) {
        if (!TextUtils.isEmpty(results.get(position).getState())) {
            String state = context.getString(R.string.comma, results.get(position).getState());
            holder.binding.itemState.setText(state);
        } else {
            holder.binding.itemState.setText("");
        }
    }

    private String setCity(int position) {
        String language = Locale.getDefault().getLanguage();
        String city;
        if (language.equals("ru")) {
            if (results.get(position).getLocal_names() != null
                    && results.get(position).getLocal_names().getRu() != null) {
                city = results.get(position).getLocal_names().getRu();
            } else {
                city = results.get(position).getName();
            }
            return city;
        } else if (language.equals("en")) {
            if (results.get(position).getLocal_names() != null
                    && results.get(position).getLocal_names().getEn() != null) {
                city = results.get(position).getLocal_names().getEn();
            } else {
                city = results.get(position).getName();
            }
            return city;
        } else if (language.equals("uk")) {
            if (results.get(position).getLocal_names() != null
                    && results.get(position).getLocal_names().getUk() != null) {
                city = results.get(position).getLocal_names().getUk();
            } else {
                city = results.get(position).getName();
            }
            return city;
        } else {
            if (results.get(position).getLocal_names() != null
                    && results.get(position).getLocal_names().getEn() != null) {
                city = results.get(position).getLocal_names().getEn();
            } else {
                city = results.get(position).getName();
            }
            return city;
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemCityResultBinding binding;

        public ItemViewHolder(@NonNull ItemCityResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}