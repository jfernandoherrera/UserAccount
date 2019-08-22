package com.jose.herrera.todo1test.view.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;
import com.jose.herrera.todo1test.app.Todo1;

public class AppTextInputLayout extends TextInputLayout {

    public AppTextInputLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

        Typeface typeface = ((Todo1)context.getApplicationContext()).getRobotoMedium();

        setTypeface(typeface);

    }


}