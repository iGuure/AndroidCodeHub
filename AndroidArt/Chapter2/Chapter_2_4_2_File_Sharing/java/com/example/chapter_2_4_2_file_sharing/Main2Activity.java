package com.example.chapter_2_4_2_file_sharing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recoverFromFile();
    }

    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Data data = null;
                File cachedFile = new File(getFilesDir(), "cache.txt");
                if (cachedFile.exists()) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(cachedFile));
                        data = (Data) objectInputStream.readObject();
                        Log.d("TAG", "recover data: " + data);
                        Log.d("TAG", "recover data num: " + data.getNum());
                        objectInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
