package com.android.testmvvm.rxjavaroom;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CacheEntity.class}, version = 1, exportSchema = false)
public abstract class DatabaseConfig extends RoomDatabase {
    public abstract CacheDao mCacheDao();
}
