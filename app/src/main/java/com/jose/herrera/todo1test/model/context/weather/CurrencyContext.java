package com.jose.herrera.todo1test.model.context.weather;

import com.jose.herrera.todo1test.model.context.weather.remote.CurrencyConverterRemote;
import com.jose.herrera.todo1test.model.exceptions.NoInternetAccessException;
import com.jose.herrera.todo1test.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class CurrencyContext {

    public static void getCurrency(AppCompatActivity compatActivity, CurrencyCompletion currencyCompletion) throws NoInternetAccessException {

        if (Utils.isNetworkAvailable(compatActivity)) {

            CurrencyConverterRemote.getCurrency(currencyCompletion);

        }else {

            throw new NoInternetAccessException(compatActivity);

        }

    }

}
