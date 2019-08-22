package com.jose.herrera.todo1test.model.context.weather.remote;

import com.jose.herrera.todo1test.model.domain.weather.WeatherResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherRemoteService {

    @GET(WeatherEndpoints.GET_WEATHER)
    Call<WeatherResponse> getWeather(@QueryMap Map<String, String> params);
}
