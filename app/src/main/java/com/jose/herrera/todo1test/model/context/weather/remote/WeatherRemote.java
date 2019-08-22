package com.jose.herrera.todo1test.model.context.weather.remote;

import android.util.Log;

import com.jose.herrera.todo1test.model.context.weather.WeatherCompletion;
import com.jose.herrera.todo1test.model.domain.weather.WeatherResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRemote {

    private final static String APP_ID = "67c0c174ae3651c1b5231be60cdd02cc";
    private final static String DESCRIPTION_LANGUAGE = "es";

    public static void getWeather(final WeatherCompletion weatherCompletion, String lat, String lon) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeatherEndpoints.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();

        WeatherRemoteService remoteService = retrofit.create(WeatherRemoteService.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put(WeatherEndpoints.LATITUDE_PARAMETER, lat);
        params.put(WeatherEndpoints.LONGITUDE_PARAMETER, lon);
        params.put(WeatherEndpoints.LANGUAGE_PARAMETER, DESCRIPTION_LANGUAGE);
        params.put(WeatherEndpoints.APP_ID_PARAMETER, APP_ID);

        Call<WeatherResponse> weather = remoteService.getWeather(params);

        weather.enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if(response.isSuccessful()) {

                    WeatherResponse response1 = response.body();

                    weatherCompletion.onGetWeather(response1.getWeather().get(0), null);

                }else {

                    weatherCompletion.onGetWeather(null, response.message());
                    Log.e("tww w ", response.message() + " ");
                }

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

                weatherCompletion.onGetWeather(null, t.getMessage());
                Log.e("wewew","fef " + t.getMessage());

            }

        });

    }

}
