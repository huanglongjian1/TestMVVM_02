package com.android.test2mvvm.test4.fragment3.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {User_bean.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract User_bean_Dao user_bean_dao();
}
