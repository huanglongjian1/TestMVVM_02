package com.android.testmvvm.rxjavaroom;

public final class DatabaseSession {

    private DatabaseSession(){}

    public static DatabaseConfig get() {
        return DatabaseInitialize.getDatabaseConfig();
    }
}