package com.example.chapter_2_4_2_file_sharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        persistToFile();
    }

    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Data data = new Data(13);
                File cacheFile = new File(getFilesDir(), "cache.txt");
                ObjectOutputStream objectOutputStream = null;
                // 使用线程同步限制多个线程的写操作
                synchronized (this) {
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheFile));
                        objectOutputStream.writeObject(data);
                        Log.d("TAG", "persist data: " + data);
                        Log.d("TAG", "persist data num: " + data.getNum());
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
