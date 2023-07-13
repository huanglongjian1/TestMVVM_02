package com.android.test2mvvm.room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.test2mvvm.util.Loge;

import retrofit2.Converter;

@Database(entities = {User_stu.class}, version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //对数据库结构进行修改的操作
            database.execSQL("ALTER TABLE user_stu"
                    + " ADD COLUMN  sex INTEGER NOT NULL DEFAULT 0");
            Loge.e("增加sex一列");
        }
    };
   public static Migration MIGRATION_2_3= new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
         //  添加新的表
            database.execSQL("CREATE TABLE IF NOT EXISTS Book (bookName TEXT, studentId TEXT, bookId PRIMARY KEY )");

        }
    };

}
