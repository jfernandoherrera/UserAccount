package com.jose.herrera.todo1test.model.domain.account;

public class Account {

    private String id;
    private int balance;
    private String userId;

    public Account(String id, int balance, String userId) {

        this.id = id;
        this.balance = balance;
        this.userId = userId;

    }

    public String getId() {

        return id;

    }

    public int getBalance() {

        return balance;

    }

    public String getUserId() {

        return userId;

    }

    @Override
    public String toString() {

        return id + " " + AccountAttributes.BALANCE + " "

                + balance + AccountAttributes.USER_ID + " " + userId;

    }

}
