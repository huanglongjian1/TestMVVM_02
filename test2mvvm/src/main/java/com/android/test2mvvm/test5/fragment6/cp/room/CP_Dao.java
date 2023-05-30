package com.android.test2mvvm.test5.fragment6.cp.room;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CP_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CP_Bean... cpBeans);

    @Delete
    void delete(CP_Bean... cpBeans);

    @Query("select * from user")
    List<CP_Bean> getAll();

    @Query("select * from " + CP_Bean.TABLE_NAME + " where " + CP_Bean.COLUMN_ID + " = :id")
    Cursor selectByID(int id);

    @Query("select " + CP_Bean.COLUMN_ID + "," + CP_Bean.COLUMN_NAME + " from " + CP_Bean.TABLE_NAME)
    Cursor selectByColumn();

    @Query("SELECT * FROM user WHERE " + CP_Bean.COLUMN_NAME + " LIKE '%' || :name || '%' ")
    Cursor selectByName(String name);

    @Update
    void update(CP_Bean... cpBeans);

}
