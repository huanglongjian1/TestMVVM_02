package com.android.test2mvvm.test1.fragment8.bean;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DB_NAME = "UserDataBase.db";
    private static volatile UserDatabase instance;


    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = createDatabase(context);
        }
        return instance;
    }

    private static UserDatabase createDatabase(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, DB_NAME).addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        }).build();
    }

    public abstract UserDao getUserDao();
}
