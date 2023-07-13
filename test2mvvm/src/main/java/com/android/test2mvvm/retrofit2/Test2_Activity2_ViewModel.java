package com.android.test2mvvm.retrofit2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.android.test2mvvm.room.AppDatabase;
import com.android.test2mvvm.room.User_stu;

import java.util.List;

public class Test2_Activity2_ViewModel extends AndroidViewModel {
    AppDatabase database;
    LiveData<List<User_stu>> listLiveData;

    public Test2_Activity2_ViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, "test2_activity_viewmodel.db").build();
        listLiveData = database.userDao().getAll_liveData();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public LiveData<List<User_stu>> getListLiveData() {
        return listLiveData;
    }
}
