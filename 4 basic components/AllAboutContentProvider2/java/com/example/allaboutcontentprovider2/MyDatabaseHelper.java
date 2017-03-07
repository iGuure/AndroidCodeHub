package com.example.allaboutcontentprovider2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Guure on 2017/3/6.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_AWARD = "create table AwardList ("
            + "id integer primary key autoincrement, "
            + "award text, "
            + "winner text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_AWARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists AwardList");
        onCreate(db);
    }
}
