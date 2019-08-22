package com.jose.herrera.todo1test.view.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.view.views.AppEditText;

import androidx.fragment.app.Fragment;

public class SignInFragment extends Fragment {

    AppEditText fieldEmail;
    AppEditText fieldPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        fieldEmail = rootView.findViewById(R.id.field_email);

        fieldPassword = rootView.findViewById(R.id.field_password);

        return rootView;

    }

    public String getEmail() {

        return fieldEmail.getText().toString();

    }

    public String getPassword() {

        return fieldPassword.getText().toString();

    }

}
