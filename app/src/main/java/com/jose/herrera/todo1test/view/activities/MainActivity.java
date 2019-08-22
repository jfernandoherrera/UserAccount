package com.jose.herrera.todo1test.view.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.app.AppError;
import com.jose.herrera.todo1test.model.context.user.UserCompletion;
import com.jose.herrera.todo1test.model.context.user.UserContext;
import com.jose.herrera.todo1test.model.context.weather.WeatherCompletion;
import com.jose.herrera.todo1test.model.context.weather.WeatherContext;
import com.jose.herrera.todo1test.model.domain.Account;
import com.jose.herrera.todo1test.model.domain.weather.Weather;
import com.jose.herrera.todo1test.model.exceptions.NoInternetAccessException;
import com.jose.herrera.todo1test.view.fragments.SignInFragment;
import com.jose.herrera.todo1test.view.fragments.UserAccountsFragment;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends BaseActivity implements UserCompletion, WeatherCompletion {

    private Weather weather;
    private UserContext userContext;
    private WeatherContext weatherContext;
    private final static String SIGN_IN_FRAGMENT_TAG = "signIn";
    private final static String USER_ACCOUNTS_FRAGMENT_TAG = "userAccounts";
    private final static int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userContext = new UserContext(this);

        weatherContext = new WeatherContext(this);

        addSignInFragment();

    }

    private void addSignInFragment() {

        SignInFragment signInFragment = new SignInFragment();

        replaceFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);

    }

    private void addUserAccountsFragment(List<Account> accounts) {

        UserAccountsFragment userAccountsFragment = new UserAccountsFragment();

        replaceFragment(userAccountsFragment, USER_ACCOUNTS_FRAGMENT_TAG);

        userAccountsFragment.setPosts(accounts);

        if (weather != null) {

            userAccountsFragment.setWeather(weather);

        }

        userAccountsFragment.setEmail(userContext.getUserEmail());

    }

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, newFragment, tag).commit();

    }

    private Fragment getFragment(String tag) {

        return getSupportFragmentManager().findFragmentByTag(tag);

    }

    public void onSignInClick(View view) {

        SignInFragment signInFragment = (SignInFragment) getFragment(SIGN_IN_FRAGMENT_TAG);

        if (signInFragment != null) {

            showProgressDialog();

            String email = signInFragment.getEmail();

            String password = signInFragment.getPassword();

            try {

                userContext.signIn(email, password, MainActivity.this);

            } catch (NoInternetAccessException e) {

                showAlert(e.getMessage());

            }

        }

    }

    @Override
    public void onSignInCompletion(AppError appError, FirebaseUser user) {

        hideProgressDialog();

        if (appError == null) {

            userContext.getAccounts();

        } else if (appError.getErrorCode() == AppError.undefinedAppErrorCode) {

            showAlert(appError.getDomain());

        } else if (appError.getErrorCode() == AppError.FirebaseAuthInvalidUserExceptionAppErrorCode) {

            showAlert(getString(R.string.firebase_user_invalid));

        } else if (appError.getErrorCode() == AppError.FirebaseAuthInvalidCredentialsExceptionAppErrorCode) {

            showAlert(getString(R.string.firebase_password_invalid));

        }

    }

    @Override
    public void onGetUserAccounts(List<Account> accounts, String errorMessage) {

        if (errorMessage == null) {

            addUserAccountsFragment(accounts);

            getWeather();

        }else {

            showAlert(errorMessage);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case LOCATION_PERMISSION_REQUEST_CODE: {

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getWeather();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }

        }

    }

    private void getWeather() {

        final FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            showAlert(getString(R.string.location_permission_needed));

            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};

            requestPermissions(permissions,1 );

        }else {

            fusedLocationClient.getLastLocation()

                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {

                            if (location != null) {

                                try {

                                    weatherContext.getWeather(MainActivity.this, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));

                                } catch (NoInternetAccessException e) {

                                    showAlert(e.getMessage());

                                }

                            }
                        }
                    });

        }

    }

    @Override
    public void onGetWeather(Weather weather, String errorMessage) {

        if(weather != null) {

            this.weather = weather;

            UserAccountsFragment userAccountsFragment = (UserAccountsFragment) getFragment(USER_ACCOUNTS_FRAGMENT_TAG);

            userAccountsFragment.setWeather(weather);

        }else {

            showAlert(errorMessage);

        }

    }

}