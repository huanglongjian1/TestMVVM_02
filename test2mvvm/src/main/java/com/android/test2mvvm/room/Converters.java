package com.android.test2mvvm.room;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Son from_Sonname(String sonname) {
        return sonname == null ? null : new Son(sonname);
    }

    @TypeConverter
    public static String dateToTimestamp(Son son) {
        return son == null ? null : son.getSon_name();
    }
}
