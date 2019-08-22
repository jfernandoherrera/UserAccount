package com.jose.herrera.todo1test.model.context.user;

import android.app.Activity;

import com.jose.herrera.todo1test.model.context.user.firebase.FirebaseRealtimeDataBaseImpl;
import com.jose.herrera.todo1test.model.context.user.firebase.FirebaseUserImpl;
import com.jose.herrera.todo1test.model.exceptions.NoInternetAccessException;
import com.jose.herrera.todo1test.utils.Utils;
import androidx.appcompat.app.AppCompatActivity;

public class UserContext {

    private FirebaseUserImpl user;
    private FirebaseRealtimeDataBaseImpl firebaseRealtimeDataBase;
    private UserCompletion userCompletion;

    public UserContext(UserCompletion userCompletion) {

        this.userCompletion = userCompletion;

        user = new FirebaseUserImpl();

        firebaseRealtimeDataBase = new FirebaseRealtimeDataBaseImpl(userCompletion);

    }

    public void signIn(final String email, final String password, Activity compatActivity ) throws NoInternetAccessException {

        if (Utils.isNetworkAvailable(compatActivity)) {

            user.signIn(email, password, compatActivity, userCompletion);

        }else {

            throw new NoInternetAccessException(compatActivity);

        }

    }

    public void getAccounts() {

        firebaseRealtimeDataBase.getAccount();

    }

    public String getUserEmail() {

        return user.getEmail();

    }

    public String getUserUid() {

        return user.getUid();

    }

}
