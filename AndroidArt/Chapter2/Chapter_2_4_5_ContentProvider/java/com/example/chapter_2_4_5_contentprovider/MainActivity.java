package com.example.chapter_2_4_5_contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.Provider;

/**
 *
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━感觉萌萌哒━━━━━━
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri dataUri = Uri.parse("content://com.example.chapter_2_4_5_contentprovider.DataProvider/data");

        // current state: 13 James Harden, 23 Lebron James

        // insert: 8 Michael Beasley
        ContentValues values = new ContentValues();
        values.put("_id", 8);
        values.put("name", "Michael Beasley");
        getContentResolver().insert(dataUri, values);

        // update: 13 James Harden -> 13 Allen Guo
        ContentValues values1 = new ContentValues();
        values1.put("_id", 13);
        values1.put("name", "Allen Guo");
        String selection = "_id = ?";
        String[] selectionArgs = {String.valueOf(13)};
        getContentResolver().update(dataUri, values1, selection, selectionArgs);

        // delete: 23 Lebron James
        String selection1 = "_id = ?";
        String[] selectionArgs1 = {String.valueOf(23)};
        getContentResolver().delete(dataUri, selection1, selectionArgs1);

        // query: 8 Michael Beasley, 13 Allen Guo
        Cursor dataCursor = getContentResolver().query(dataUri, new String[] {"_id", "name"}, null, null, null);
        while(dataCursor.moveToNext()) {
            Data data = new Data();
            data.set_id(dataCursor.getInt(0));
            data.setName(dataCursor.getString(1));
            Log.d(TAG, "query data:" + data.get_id() + " " + data.getName());
        }
        dataCursor.close();

        // call()支持自定义调用
        // call meathod "goat": 删除data表中所有球员信息，添加一名最伟大的球员
        Bundle bundle = new Bundle();
        // call()需要的参数通过bundle传输
        bundle.putString("uri", "content://com.example.chapter_2_4_5_contentprovider.DataProvider/data");
        bundle.putInt("_id", 23);
        bundle.putString("name", "Michael Jordan");
        // 注意：这里第一个参数指明的是ContentProvider的URI
        // 由于ContentProvider知道自己的URI，所以ContentProvider中的call()没有这个参数
        getContentResolver().call(DataProvider.CONTENT_URI, "goat", null, bundle);

        dataCursor = getContentResolver().query(dataUri, new String[] {"_id", "name"}, null, null, null);
        while(dataCursor.moveToNext()) {
            Data data = new Data();
            data.set_id(dataCursor.getInt(0));
            data.setName(dataCursor.getString(1));
            Log.d(TAG, "query data:" + data.get_id() + " " + data.getName());
        }
        dataCursor.close();

    }
}
