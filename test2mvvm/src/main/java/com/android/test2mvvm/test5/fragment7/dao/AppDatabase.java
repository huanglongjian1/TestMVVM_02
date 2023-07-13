package com.android.test2mvvm.test5.fragment7.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.test2mvvm.test5.fragment7.bean.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "mvvm_demo.db";
    private static volatile AppDatabase mInstance;

    /**
     * 单例模式
     */
    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AppDatabase.class) {
                if (mInstance == null) {
                    mInstance =Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME).build();
                }
            }
        }
        return mInstance;
    }

    public abstract UserDao userDao();
}
