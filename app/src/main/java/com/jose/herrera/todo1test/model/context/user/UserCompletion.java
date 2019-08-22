package com.jose.herrera.todo1test.model.context.user;

import com.google.firebase.auth.FirebaseUser;
import com.jose.herrera.todo1test.app.AppError;
import com.jose.herrera.todo1test.model.domain.Account;
import java.util.List;

public interface UserCompletion {

    void onSignInCompletion(AppError appError, FirebaseUser user);

    void onGetUserAccounts(List<Account> accounts, String errorMessage);

}
