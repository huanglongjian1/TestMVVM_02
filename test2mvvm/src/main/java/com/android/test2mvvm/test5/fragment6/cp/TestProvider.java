package com.android.test2mvvm.test5.fragment6.cp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.test5.fragment6.cp.room.CP_AppDatabase;
import com.android.test2mvvm.test5.fragment6.cp.room.CP_Bean;
import com.android.test2mvvm.test5.fragment6.cp.room.CP_Dao;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class TestProvider extends ContentProvider {
    CP_Dao cp_dao;
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String AUTHORITY = "test2mvvm.testprovider";
    public static final int USER_URI_CODE = 0;

    static {
        mUriMatcher.addURI(AUTHORITY, CP_Bean.TABLE_NAME, USER_URI_CODE);
    }

    private String getTableName(Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case USER_URI_CODE:
                return CP_Bean.TABLE_NAME;
            default:
                return "uri错误";
        }

    }


    @Override
    public boolean onCreate() {
        Loge.e("onCreate+---------------");
        CP_AppDatabase db = CP_AppDatabase.creat_database(getContext());
        cp_dao = db.cp_dao();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        Loge.e(tableName);
        Cursor cursor = cp_dao.selectByName("bean");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


}
