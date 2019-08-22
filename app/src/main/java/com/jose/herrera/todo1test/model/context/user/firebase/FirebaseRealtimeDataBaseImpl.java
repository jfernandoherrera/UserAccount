package com.jose.herrera.todo1test.model.context.user.firebase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jose.herrera.todo1test.model.context.user.UserCompletion;
import com.jose.herrera.todo1test.model.domain.Account;
import com.jose.herrera.todo1test.model.domain.AccountAttributes;
import java.util.ArrayList;
import java.util.List;

public class FirebaseRealtimeDataBaseImpl {

    private FirebaseFirestore db;
    private UserCompletion userCompletion;

    public FirebaseRealtimeDataBaseImpl(UserCompletion userCompletion) {

        this.userCompletion = userCompletion;

        db = FirebaseFirestore.getInstance();

    }

    public void getAccount() {

        db.collection(AccountAttributes.COLLECTION_ACCOUNT)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete( Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            List<Account> accounts = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                int balance =  Integer.parseInt(document.getData().get(AccountAttributes.BALANCE).toString());

                                String userId = document.getData().get(AccountAttributes.USER_ID).toString();

                                Account account = new Account(document.getId(), balance, userId);

                                accounts.add(account);

                            }

                            userCompletion.onGetUserAccounts(accounts, null);

                        } else {

                            userCompletion.onGetUserAccounts(null, "Error getting documents. " + task.getException().getMessage());

                        }

                    }
                });

    }

}
