package com.android.test2mvvm.test5.fragment7.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.test5.fragment7.testroom.SingleLiveEvent;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user")
    List<User> getAll_01();

    @Query("select * from user where uid = 1")
    LiveData<User> getUser01();

    @Update
    void update(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

}
