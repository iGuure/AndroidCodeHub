package com.example.chapter_2_2_multiprocess;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by Guure on 2017/5/9.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        /* 运行在不同进程中的组件是属于两个不同的虚拟机和Application的 */
        int processId = Process.myPid();
        Log.d("TAG", "application start, process id: " + processId);
    }
}
