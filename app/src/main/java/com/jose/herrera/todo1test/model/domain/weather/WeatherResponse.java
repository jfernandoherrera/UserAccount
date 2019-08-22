package com.jose.herrera.todo1test.model.domain.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("weather")
    private List<Weather> weather;

    public List<Weather> getWeather() {

        return weather;

    }

}
