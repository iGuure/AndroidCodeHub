package com.example.allaboutcontentprovider2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Guure on 2017/3/7.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;

    public static final int TABLE1_ITEM = 1;

    public static final String AUTHORITY = "com.example.allaboutcontentprovider2.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "awardlist", TABLE1_DIR);
        uriMatcher.addURI(AUTHORITY, "awardlist/#", TABLE1_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "Award.db", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                // 查询table1表中的所有数据
                cursor = db.query("AwardList", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TABLE1_ITEM:
                // 查询table1表中的单条数据
                String awardId = uri.getPathSegments().get(1);
                cursor = db.query("AwardList", projection, "id = ?", new String[] { awardId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.allaboutcontentprovider2.provider.awardlist";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.allaboutcontentprovider2.provider.awardlist";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // 添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
            case TABLE1_ITEM:
                long newAwardId = db.insert("AwardList", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/awardlist/" + newAwardId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                deleteRows = db.delete("AwardList", selection, selectionArgs);
                break;
            case TABLE1_ITEM:
                String awardId = uri.getPathSegments().get(1);
                deleteRows = db.delete("AwardList", "id = ?", new String[] { awardId });
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                updatedRows = db.update("AwardList", values, selection, selectionArgs);
                break;
            case TABLE1_ITEM:
                String awardId = uri.getPathSegments().get(1);
                updatedRows = db.update("AwardList", values, "id = ?", new String[] { awardId });
                break;
            default:
                break;
        }
        return updatedRows;
    }
}
