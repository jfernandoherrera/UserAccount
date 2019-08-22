package com.jose.herrera.todo1test.view.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.jose.herrera.todo1test.app.Todo1;

import androidx.appcompat.widget.AppCompatEditText;

public class AppEditText extends AppCompatEditText {

    public AppEditText(Context context, AttributeSet attrs) {

        super(context, attrs);

        Typeface typeface = ((Todo1)context.getApplicationContext()).getRobotoMedium();

        setTypeface(typeface);

    }

}