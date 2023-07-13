package com.android.test2mvvm.room;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User_stu {
    @PrimaryKey
    public int uid;

    public Son son;
    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
    @Embedded
    public Address address;

    public int sex;

    public User_stu(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User_stu{" +
                "uid=" + uid +
                ", son=" + son +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", sex=" + sex +
                '}';
    }
}
