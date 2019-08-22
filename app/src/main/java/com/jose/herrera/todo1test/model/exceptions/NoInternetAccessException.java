package com.jose.herrera.todo1test.model.exceptions;

import android.content.Context;

import com.jose.herrera.todo1test.R;

public class NoInternetAccessException extends Exception{

    public NoInternetAccessException(Context context) {

        super(context.getString(R.string.no_internet_access));
    }

}
