package com.android.test2mvvm.test5.fragment4.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.test2mvvm.test5.fragment4.bean.Person;
import com.android.test2mvvm.util.Loge;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class Person_Database extends RoomDatabase {
    public abstract Person_Dao person_dao();

    private static Person_Database INSTANCE;//创建单例

    public static Person_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Person_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    Person_Database.class, "person_database.db")
                            .addCallback(sOnOpenCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sOnOpenCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    Loge.e("database 被打开");
                }

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Loge.e("database 被创建");
                }
            };
}
