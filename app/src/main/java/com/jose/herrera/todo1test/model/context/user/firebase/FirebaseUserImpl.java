package com.jose.herrera.todo1test.model.context.user.firebase;

import android.app.Activity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.jose.herrera.todo1test.app.AppError;
import com.jose.herrera.todo1test.model.context.user.UserCompletion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirebaseUserImpl {

    private FirebaseAuth mAuth;


    public FirebaseUserImpl() {

        mAuth = FirebaseAuth.getInstance();

    }

    public void signIn(String email, String password, Activity appCompatActivity,

                       final UserCompletion userSignInCompleteListener) {

        if(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(appCompatActivity) != ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {

            mAuth.signInWithEmailAndPassword(email, password)

                    .addOnCompleteListener(appCompatActivity, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                userSignInCompleteListener.onSignInCompletion(null, task.getResult().getUser());

                            }

                        }

                    }).addOnFailureListener( new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    if (e instanceof FirebaseAuthInvalidUserException) {

                        AppError appError = new AppError(getClass().toString(), AppError.FirebaseAuthInvalidUserExceptionAppErrorCode);
                        userSignInCompleteListener.onSignInCompletion(appError, null);

                    } else if (e instanceof FirebaseAuthInvalidCredentialsException) {

                        AppError appError = new AppError(getClass().toString(), AppError.FirebaseAuthInvalidCredentialsExceptionAppErrorCode);
                        userSignInCompleteListener.onSignInCompletion(appError, null);

                    } else {

                        AppError appError = new AppError(e.getMessage(), AppError.undefinedAppErrorCode);
                        userSignInCompleteListener.onSignInCompletion(appError, null);

                    }

                }

            });

        }
    }

    public void signOut() {

        mAuth.signOut();

    }

    public String getEmail() {

      return mAuth.getCurrentUser().getEmail();

    }

    public String getUid() {

        return mAuth.getCurrentUser().getUid();

    }
}
