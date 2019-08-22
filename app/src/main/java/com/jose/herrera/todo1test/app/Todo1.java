package com.jose.herrera.todo1test.app;

import android.app.Application;
import android.graphics.Typeface;

import com.google.firebase.FirebaseApp;

public class Todo1 extends Application {

    Typeface robotoMedium;
    Typeface robotoThin;

    @Override
    public void onCreate() {

        super.onCreate();

        FirebaseApp.initializeApp(this);

        robotoMedium = Typeface.createFromAsset(getAssets(), "fonts/roboto_medium.TTF");

        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/roboto_thin.TTF");

    }

    public Typeface getRobotoMedium() {

        return robotoMedium;

    }

    public Typeface getRobotoThin() {

        return robotoThin;

    }

}
