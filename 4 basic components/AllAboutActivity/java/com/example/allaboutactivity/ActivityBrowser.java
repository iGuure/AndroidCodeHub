package com.example.allaboutactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        /*
        * 3. 设置activity的intent-filter，使其可以响应打开浏览器的动作，并传递url
        *
        * 通过AndroidManifest.xml注册intent-filter
        * 通过intent塞数据和取数据
        *
        * */
        Intent yIntent = getIntent();
        String url = yIntent.getStringExtra("url");
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(url);

    }
}
