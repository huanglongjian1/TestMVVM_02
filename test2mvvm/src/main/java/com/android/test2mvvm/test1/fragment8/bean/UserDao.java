package com.android.test2mvvm.test1.fragment8.bean;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getUserLiveData();//在这里可以直接返回LiveData<>封装的查询结果

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
    @Query("DELETE FROM user")
    void deleteAll();

}
