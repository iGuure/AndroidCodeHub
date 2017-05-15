package com.example.chapter_2_4_5_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Guure on 2017/5/14.
 */

public class DataProvider extends ContentProvider {

    private static final String TAG = "DataProvider";

    public static final String AUTHORITY = "com.example.chapter_2_4_5_contentprovider.DataProvider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri DATA_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/data");

    public static final int DATA_URI_CODE = 0;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // 将Uri和Uri_Code关联到一起
    static {
        uriMatcher.addURI(AUTHORITY, "data", DATA_URI_CODE);
    }

    private Context context;
    private SQLiteDatabase mDb;

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (uriMatcher.match(uri)) {
            case DATA_URI_CODE:
                tableName = DBOpenHelper.DATA_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate, current thread:" + Thread.currentThread().getName());
        context = getContext();
        // ContentProvider创建时，初始化数据库。注意：这里仅仅是为了演示，实际使用中不推荐在主线程中进行耗时的数据库操作
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new DBOpenHelper(context).getWritableDatabase();
        mDb.execSQL("delete from " + DBOpenHelper.DATA_TABLE_NAME);
        mDb.execSQL("insert into data values(13, 'James harden');");
        mDb.execSQL("insert into data values(23, 'Lebron James');");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query, current thread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        mDb.insert(table, null, values);
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        // 判断需要调用的是哪个方法
        if (method.equals("goat")) {
            // 将得到的球员信息打包成ContentValues
            int _id = extras.getInt("_id");
            String name = extras.getString("name");
            ContentValues values = new ContentValues();
            values.put("_id", _id);
            values.put("name", name);

            // 调用自身的CURD方法
            Uri uri = Uri.parse(extras.getString("uri"));
            delete(uri, null, null);
            insert(uri, values);
        }
        return null;
    }
}
