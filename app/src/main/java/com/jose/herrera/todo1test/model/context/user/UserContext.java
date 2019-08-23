package com.jose.herrera.todo1test.model.context.user;

import android.app.Activity;

import com.jose.herrera.todo1test.model.context.user.firebase.FirestoreUserAccountImpl;
import com.jose.herrera.todo1test.model.context.user.firebase.FirebaseUserImpl;
import com.jose.herrera.todo1test.model.exceptions.NoInternetAccessException;
import com.jose.herrera.todo1test.utils.Utils;

public class UserContext {

    private FirebaseUserImpl user;
    private FirestoreUserAccountImpl firebaseRealtimeDataBase;
    private UserCompletion userCompletion;

    public UserContext(UserCompletion userCompletion) {

        this.userCompletion = userCompletion;

        user = new FirebaseUserImpl();

        firebaseRealtimeDataBase = new FirestoreUserAccountImpl(userCompletion);

    }

    public void signIn(final String email, final String password, Activity compatActivity ) throws NoInternetAccessException {

        if (Utils.isNetworkAvailable(compatActivity)) {

            user.signIn(email, password, compatActivity, userCompletion);

        }else {

            throw new NoInternetAccessException(compatActivity);

        }

    }

    public void getAccounts() {

        firebaseRealtimeDataBase.getAccount(user.getUid());

    }

    public void getAccounts(String userId) {

        firebaseRealtimeDataBase.getAccount(userId);

    }

    public String getUserEmail() {

        return user.getEmail();

    }

    public String getUserUid() {

        return user.getUid();

    }

    public void transfer(int amount, String fromId, String toId) {

        firebaseRealtimeDataBase.transfer(amount, fromId, toId);

    }

}
