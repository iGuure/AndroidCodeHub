package com.example.slidingconflicttest;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String[] data;

    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        Resources resources = this.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        Log.d(TAG, screenWidth + "");
        Log.d(TAG, screenHeight + "");

        data = new String[]{"Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple"};
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView = (ListView) findViewById(R.id.content1);
        listView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight));
        listView.setAdapter(adapter);

        data = new String[]{"orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange"};
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView = (ListView) findViewById(R.id.content2);
        listView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight));
        listView.setAdapter(adapter);

        data = new String[]{"peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach", "peach"};
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView = (ListView) findViewById(R.id.content3);
        listView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight));
        listView.setAdapter(adapter);

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
