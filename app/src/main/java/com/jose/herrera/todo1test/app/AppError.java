package com.jose.herrera.todo1test.app;

public class AppError {

    public static final int FirebaseAuthWeakPasswordAppErrorCode = -11111;
    public static final int FirebaseAuthUserCollisionAppErrorCode = -22222;
    public static final int FirebaseAuthInvalidUserExceptionAppErrorCode = -33333;
    public static final int FirebaseAuthInvalidCredentialsExceptionAppErrorCode = -44444;
    public static final int undefinedAppErrorCode = 0;
    public static final int valueAlreadyExists = -55545;
    public static final int noInternetAccess = - 66656;
    public static final int cantLoginWithRealm = -75481;
    private int errorCode;
    private String domain;

    public AppError(String domain, int errorCode) {

        this.errorCode = errorCode;

        this.domain = domain;

    }

    public int getErrorCode() {

        return errorCode;

    }

    public String getDomain() {

        return domain;

    }

    public void addDomain(String domain) {

        this.domain = domain + " " + domain;

    }
}