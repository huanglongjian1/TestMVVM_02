package com.android.test2mvvm.test5.fragment6.cp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {CP_Bean.class}, version = 1)
public abstract class CP_AppDatabase extends RoomDatabase {
    public abstract CP_Dao cp_dao();

    public static CP_AppDatabase creat_database(Context context) {
        CP_AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        CP_AppDatabase.class, "cp.db")
                .allowMainThreadQueries()
                .build();
        return db;
    }
}
