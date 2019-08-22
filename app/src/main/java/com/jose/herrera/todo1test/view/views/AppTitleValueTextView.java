package com.jose.herrera.todo1test.view.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.app.Todo1;
import com.jose.herrera.todo1test.utils.CustomSpanTypeface;

import androidx.appcompat.widget.AppCompatTextView;

public class AppTitleValueTextView extends AppCompatTextView {

    private String title;
    private Typeface typeface;

    public AppTitleValueTextView(Context context) {

        this(context, null);

        }


    public AppTitleValueTextView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);

        }


    public AppTitleValueTextView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AppTextView, 0, 0);

        String font = a.getString(R.styleable.AppTextView_fontText) != null ? a.getString(R.styleable.AppTextView_fontText) : "medium";

        if(font.equals("medium")) {

        typeface = ((Todo1) context.getApplicationContext()).getRobotoMedium();

        setTypeface(typeface);

        }else {

       typeface = ((Todo1) context.getApplicationContext()).getRobotoThin();

        setTypeface(typeface);

        }

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public void setText(String text) {

        if(title != null) {

            text = title + " " + text;

            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);

            stringBuilder.setSpan(new CustomSpanTypeface(null, Typeface.BOLD, (int) getTextSize(), ColorStateList.valueOf(getContext().getResources().getColor(R.color.blue)), null, typeface), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            stringBuilder.setSpan(new CustomSpanTypeface(null, Typeface.BOLD, (int) getTextSize(), null, null, typeface), title.length() + 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            super.setText(stringBuilder);

        }else {

            super.setText(text);

        }

    }


}