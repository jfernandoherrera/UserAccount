package com.jose.herrera.todo1test.model.domain.currency;

import com.google.gson.annotations.SerializedName;

public class CurrencyResponse {

    @SerializedName("quotes")
    Quotes quotes;

    public Quotes getQuotes() {

        return quotes;
    }

}
