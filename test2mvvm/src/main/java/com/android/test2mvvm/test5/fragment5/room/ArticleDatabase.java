package com.android.test2mvvm.test5.fragment5.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticleDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "article.db";

    private static ArticleDatabase databaseInstance;

    public static synchronized ArticleDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), ArticleDatabase.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

    public abstract ArticleDao articleDao();
}
