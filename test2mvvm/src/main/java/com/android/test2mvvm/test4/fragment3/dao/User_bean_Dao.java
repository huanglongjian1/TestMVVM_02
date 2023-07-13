package com.android.test2mvvm.test4.fragment3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.test2mvvm.room.User_stu;

import java.util.List;

@Dao
public interface User_bean_Dao {
    @Query("SELECT * FROM user_bean")
    List<User_bean> getAll();

    @Query("SELECT * FROM user_bean")
    LiveData<List<User_bean>> getAll_liveData();

    @Query("SELECT * FROM user_bean WHERE id IN (:ints)")
    List<User_bean> loadAllByIds(int... ints);


    @Query("SELECT * FROM user_bean WHERE id=:id")
    LiveData<User_bean> findByID(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User_bean... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_List(List<User_bean> list);

    @Delete
    void delete(User_bean... user);

    @Update
    void updateUsers(User_bean... users);
}
