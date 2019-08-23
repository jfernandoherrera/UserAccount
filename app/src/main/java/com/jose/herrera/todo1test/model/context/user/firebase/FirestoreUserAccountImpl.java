package com.jose.herrera.todo1test.model.context.user.firebase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.jose.herrera.todo1test.model.context.user.UserCompletion;
import com.jose.herrera.todo1test.model.domain.account.Account;
import com.jose.herrera.todo1test.model.domain.account.AccountAttributes;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FirestoreUserAccountImpl {

    private FirebaseFirestore db;
    private UserCompletion userCompletion;

    public FirestoreUserAccountImpl(UserCompletion userCompletion) {

        this.userCompletion = userCompletion;

        db = FirebaseFirestore.getInstance();

    }

    public void getAccount(final String userId) {

        db.collection(AccountAttributes.COLLECTION_ACCOUNT).whereEqualTo(AccountAttributes.USER_ID, userId)
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


    public void transfer(final int amount, final String fromId, final String toId) {

        DocumentReference from = db.collection(AccountAttributes.COLLECTION_ACCOUNT).document(fromId);

        from.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    final DocumentSnapshot snapshot1 = task.getResult();

                    final Integer newFromBalance = Integer.parseInt(snapshot1.get(AccountAttributes.BALANCE).toString()) - amount;

                    DocumentReference to = db.collection(AccountAttributes.COLLECTION_ACCOUNT).document(toId);

                    to.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.isSuccessful()) {

                                final DocumentSnapshot snapshot = task.getResult();

                                final Integer newToBalance = Integer.parseInt(snapshot.get(AccountAttributes.BALANCE).toString()) + amount;

                                db.runTransaction(new Transaction.Function<Object>() {

                                    @Nullable
                                    @Override
                                    public Object apply(@NonNull Transaction transaction) {

                                        snapshot.getReference().update(AccountAttributes.BALANCE, newToBalance);

                                        snapshot1.getReference().update(AccountAttributes.BALANCE, newFromBalance);

                                        userCompletion.onTransferCompleted();

                                        return null;

                                    }

                                });

                            }

                        }

                    });

                }

            }

        });

       // from.update(AccountAttributes.BALANCE, Integer.parseInt(from.get().getResult().get(AccountAttributes.BALANCE).toString()) - amount);

    }
}
