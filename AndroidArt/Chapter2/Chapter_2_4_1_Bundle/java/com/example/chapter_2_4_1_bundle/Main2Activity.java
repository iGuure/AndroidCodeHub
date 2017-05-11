package com.example.chapter_2_4_1_bundle;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
            Log.d("TAG", bundle.getInt("key") + "");

        button = (Button) findViewById(R.id.goToAnotherApp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle newBundle = new Bundle();
                newBundle.putInt("key", 23);

                /* 不同应用之间的通讯 */
                Intent goToAnotherAppIntent = new Intent();
                /* 两个参数分别为包名，Activity的名字（全称） */
                ComponentName componentName = new ComponentName("com.example.chapter_2_4_1_bundle2", "com.example.chapter_2_4_1_bundle2.MainActivity");
                goToAnotherAppIntent.setComponent(componentName);
                goToAnotherAppIntent.putExtras(newBundle);
                startActivity(goToAnotherAppIntent);
            }
        });

    }
}
