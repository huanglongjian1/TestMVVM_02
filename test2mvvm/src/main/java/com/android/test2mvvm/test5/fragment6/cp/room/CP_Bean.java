package com.android.test2mvvm.test5.fragment6.cp.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = CP_Bean.TABLE_NAME)
public class CP_Bean {
    public static final String TABLE_NAME = "user";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ID = "_id";

    @PrimaryKey
    int _id;
    String name;

    public CP_Bean(int _id, String name) {
        this._id = _id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CP_Bean{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                '}';
    }
}
