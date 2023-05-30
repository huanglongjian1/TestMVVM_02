package com.android.testmvvm.rxjavaroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CacheDao {
    @Insert
    void insertCaches(CacheEntity... cacheEntities);

    @Update
    void updateCaches(CacheEntity... cacheEntities);

    @Query("SELECT * FROM cache WHERE `key` = :key LIMIT 0,1")
    CacheEntity findByKey(String key);

    @Delete
    void deleteCaches(CacheEntity... cacheEntities);

    @Query("SELECT * FROM cache")
    CacheEntity[] findAll();

    @Insert
    Completable insert(CacheEntity cacheEntity);

    @Query("Select * from cache")
    Flowable<List<CacheEntity>> getAll();

    @Query("Select * from cache")
    LiveData<List<CacheEntity>> getAll_livedata();
}