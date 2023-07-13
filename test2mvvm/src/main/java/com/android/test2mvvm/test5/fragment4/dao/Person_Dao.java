package com.android.test2mvvm.test5.fragment4.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.test2mvvm.test5.fragment4.bean.Person;

import java.util.List;
@Dao
public interface Person_Dao {
    @Query("select * from person")
    List<Person> get_All();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person... person);

    @Delete
    void delete(Person... person);

    @Update
    void update(Person... person);

}
