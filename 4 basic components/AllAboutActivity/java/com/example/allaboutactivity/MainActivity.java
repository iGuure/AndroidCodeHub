package com.example.allaboutactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data");
                    Log.d("data", returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * 1. 显示Intent的调用
         *
         * 直接跳转到Activity 2
         *
         * */
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xIntent = new Intent(MainActivity.this, Activity2.class);
                startActivity(xIntent);
            }
        });

        /*
        * 2. 在App中打开网页
        *
        * 通过隐式Intent + data实现
        *
        * */
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.baidu.com";
                Intent yIntent = new Intent(Intent.ACTION_VIEW);
                yIntent.setData(Uri.parse(url));
                yIntent.putExtra("url", url);
                startActivity(yIntent);
            }
        });

        /*
        * 5. startActivityForResult的实现
        *
        * 注意：不能在Activity3的onDestroy写返回数据
        * 因为那个时候已经错过onActivityResult()的发动时机
        *
        * */
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivityForResult(intent, 1);
            }
        });


    }
}
