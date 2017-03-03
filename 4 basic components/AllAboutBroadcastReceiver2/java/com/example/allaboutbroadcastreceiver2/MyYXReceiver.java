package com.example.allaboutbroadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Guure on 2017/3/3.
 */

public class MyYXReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "receive your yxb in 2st!", Toast.LENGTH_SHORT).show();
    }
}
