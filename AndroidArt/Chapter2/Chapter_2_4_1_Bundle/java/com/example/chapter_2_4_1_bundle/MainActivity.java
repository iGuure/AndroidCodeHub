package com.example.chapter_2_4_1_bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("key", 13);

                /* 同一应用不同进程之间的通讯 */
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
