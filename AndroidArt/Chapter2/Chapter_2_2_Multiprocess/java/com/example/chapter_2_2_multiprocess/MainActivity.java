package com.example.chapter_2_2_multiprocess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", Thread.currentThread().getName());
        /*
         * Android为每个进程分配了一个独立的虚拟机，不用的虚拟机在内存分配上有不同的地址空间
         * 在一个进程中修改sUserId的值对其他进程不会造成任何影响
         */
        UserManager.sUserId = 2;
        Log.d("MainActivity", UserManager.sUserId + "");
        button = (Button) findViewById(R.id.goToSecondActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
