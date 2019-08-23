package com.jose.herrera.todo1test.model.context.currency.remote;

import com.jose.herrera.todo1test.model.domain.currency.CurrencyResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface CurrencyConverterRemoteService {

    @GET(CurrencyEndpoints.GET_CURRENT_CURRENCY)
    Call<CurrencyResponse> getCurrency(@QueryMap Map<String, String> params);

}
