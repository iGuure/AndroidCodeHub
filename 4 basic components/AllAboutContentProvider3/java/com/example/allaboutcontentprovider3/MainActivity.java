package com.example.allaboutcontentprovider3;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加数据
                Uri uri = Uri.parse("content://com.example.allaboutcontentprovider2.provider/awardlist");
                ContentValues values = new ContentValues();
                values.put("award", "2016-17 Rookie of the league");
                values.put("winner", "Joel Embiid");
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.allaboutcontentprovider2.provider/awardlist");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String award = cursor.getString(cursor.getColumnIndex("award"));
                        String winner = cursor.getString(cursor.getColumnIndex("winner"));
                        Log.d("MainActivity", "award is " + award);
                        Log.d("MainActivity", "winner is " + winner);
                    }
                    cursor.close();
                }
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.allaboutcontentprovider2.provider/awardlist/" + newId);
                ContentValues values = new ContentValues();
                values.put("award", "2016-17 MVP of the league");
                values.put("winner", "Lebron James");
                getContentResolver().update(uri, values, null, null);
            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除数据
                Uri uri = Uri.parse("content://com.example.allaboutcontentprovider2.provider/awardlist/" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });
    }
}
