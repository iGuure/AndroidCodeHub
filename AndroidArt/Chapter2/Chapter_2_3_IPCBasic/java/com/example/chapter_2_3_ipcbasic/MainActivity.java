package com.example.chapter_2_3_ipcbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Serializable序列化过程
        try {
            Data data = new Data(1);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(), "cache.txt")));
            out.writeObject(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Serializable反序列化过程
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(getFilesDir(), "cache.txt")));
            Data newData = (Data) in.readObject();
            in.close();
            Log.d("TAG", newData.getNum() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
