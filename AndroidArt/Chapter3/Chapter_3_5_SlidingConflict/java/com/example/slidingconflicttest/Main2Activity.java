package com.example.slidingconflicttest;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    private String[] data;

    ArrayAdapter<String> adapter;
    ListViewEx listViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Resources resources = this.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        Log.d(TAG, screenWidth + "");
        Log.d(TAG, screenHeight + "");

        data = new String[]{"Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple"};
        adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, data);
        listViewEx = (ListViewEx) findViewById(R.id.content1);
        listViewEx.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight));
        listViewEx.setAdapter(adapter);

        data = new String[]{"orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange"};
        adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, data);
        listViewEx = (ListViewEx) findViewById(R.id.content2);
        listViewEx.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight));
        listViewEx.setAdapter(adapter);

        data = new String[]{"peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach"};
        adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, data);
        listViewEx = (ListViewEx) findViewById(R.id.content3);
        listViewEx.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight));
        listViewEx.setAdapter(adapter);
    }
}
