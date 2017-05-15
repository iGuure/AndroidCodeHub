package com.example.chapter_2_4_5_contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guure on 2017/5/14.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "data_provider.db";
    public static final String DATA_TABLE_NAME = "data";

    private static final int DB_VERSION = 1;

    private String CREATE_DATA_TABLE = "CREATE TABLE IF NOT EXISTS "
            + DATA_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT)";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO ignored
    }

}
