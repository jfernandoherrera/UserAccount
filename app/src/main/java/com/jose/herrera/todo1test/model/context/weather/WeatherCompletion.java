package com.jose.herrera.todo1test.model.context.weather;

import com.jose.herrera.todo1test.model.domain.weather.Weather;

public interface WeatherCompletion {

    void onGetWeather(Weather weather, String errorMessage);

}
