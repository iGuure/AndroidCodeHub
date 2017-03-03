package com.example.allaboutbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private NetworkChangeRecevier2 networkChangeRecevier2;

    private LocalReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * 2. 动态注册
        *
        * 接受网络连接状态变化的广播
        * 与静态注册一样，都是使用定义了一个action的intentFilter
        * 记得要在onDestroy()中取消注册
        *
        * */
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeRecevier2 = new NetworkChangeRecevier2();
        registerReceiver(networkChangeRecevier2, intentFilter);

        /*
        * 3. 发送标准广播
        *
        * 使用sendBroadcast()
        *
        * */
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.allaboutbroadcastreceiver.MY_B");
                sendBroadcast(intent);
            }
        });

        /*
        * 4. 发送有序广播
        *
        * 使用sendOrderedBroadcast()
        * 设置1st和2nd两个BroadcastReceiver
        * 设置前一个优先级为10，后一个为9
        * 在第一个BroadcastReceiver中设置中断广播
        * 因此2nd不可以接收到广播
        *
        * */
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.allaboutbroadcastreceiver.MY_YXB");
                sendOrderedBroadcast(intent, null);
            }
        });

        /*
        * 5. 发送本地广播
        *
        * 首先使用LocalBroadcastManager动态注册广播接收器！（不能静态注册）
        * 然后使用LocalBroadcastManager发送本地广播
        * 最后记得在onDestroy()取消注册广播接收器
        *
        * */
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.allaboutbroadcastreceiver.LOCAL_B");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.allaboutbroadcastreceiver.LOCAL_B");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeRecevier2);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeRecevier2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network changes from dynamic one!", Toast.LENGTH_SHORT).show();
        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast!", Toast.LENGTH_SHORT).show();
        }
    }

}
