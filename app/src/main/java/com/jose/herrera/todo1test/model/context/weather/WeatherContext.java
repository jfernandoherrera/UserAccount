package com.jose.herrera.todo1test.model.context.weather;

import com.jose.herrera.todo1test.model.context.weather.remote.WeatherRemote;
import com.jose.herrera.todo1test.model.context.weather.remote.WeatherRemoteService;
import com.jose.herrera.todo1test.model.exceptions.NoInternetAccessException;
import com.jose.herrera.todo1test.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherContext {

    private WeatherCompletion weatherCompletion;

    public WeatherContext(WeatherCompletion weatherCompletion) {


        this.weatherCompletion = weatherCompletion;

    }

    public void getWeather(AppCompatActivity compatActivity, String lat, String lon ) throws NoInternetAccessException {

        if (Utils.isNetworkAvailable(compatActivity)) {

            WeatherRemote.getWeather(weatherCompletion, lat, lon);

        }else {

            throw new NoInternetAccessException(compatActivity);

        }

    }

}
