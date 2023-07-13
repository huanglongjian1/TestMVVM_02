package com.android.test2mvvm.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_stu")
    List<User_stu> getAll();

    @Query("SELECT * FROM user_stu")
    LiveData<List<User_stu>> getAll_liveData();

    @Query("SELECT * FROM user_stu WHERE uid IN (:ints)")
    List<User_stu> loadAllByIds(int... ints);

    @Query("SELECT * FROM user_stu WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User_stu findByName(String first, String last);

    @Query("SELECT * FROM User_stu WHERE uid=:id")
    LiveData<User_stu> findByID(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User_stu... users);

    @Delete
    void delete(User_stu... user);

    @Update
    void updateUsers(User_stu... users);
}
