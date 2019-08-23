package com.jose.herrera.todo1test.model.context.currency.remote;

import com.jose.herrera.todo1test.model.context.currency.CurrencyCompletion;
import com.jose.herrera.todo1test.model.domain.currency.CurrencyResponse;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyConverterRemote {

    private final static String APP_ID = "ab333a09bf068badbf2723260ceb51db";
    private final static String FORMAT = "1";
    private final static String CURRENCIES = "COP";
    private final static String SOURCE= "USD";

    public static void getCurrency(final CurrencyCompletion currencyCompletion) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(CurrencyEndpoints.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();

        CurrencyConverterRemoteService remoteService = retrofit.create(CurrencyConverterRemoteService.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put(CurrencyEndpoints.FORMAT_PARAMETER, FORMAT);
        params.put(CurrencyEndpoints.CURRENCIES_PARAMETER, CURRENCIES);
        params.put(CurrencyEndpoints.SOURCE_PARAMETER, SOURCE);
        params.put(CurrencyEndpoints.APP_ID_PARAMETER, APP_ID);

        Call<CurrencyResponse> weather = remoteService.getCurrency(params);

        weather.enqueue(new Callback<CurrencyResponse>() {

            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {

                if(response.isSuccessful()) {

                    CurrencyResponse response1 = response.body();

                    currencyCompletion.onGetDollarToPesosValue(response1.getQuotes().getValue(), null);

                }else {

                    currencyCompletion.onGetDollarToPesosValue(0, response.message());

                }

            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {

                currencyCompletion.onGetDollarToPesosValue(0, t.getMessage());

            }

        });

    }

}
