package com.jose.herrera.todo1test.model.domain.currency;

import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("USDCOP")
    private int value;

    public int getValue() {

        return value;

    }

}
