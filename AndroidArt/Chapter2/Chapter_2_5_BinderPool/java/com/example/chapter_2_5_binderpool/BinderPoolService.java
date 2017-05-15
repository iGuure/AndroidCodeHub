package com.example.chapter_2_5_binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Guure on 2017/5/15.
 */

public class BinderPoolService extends Service {

    private static final String TAG = "BinderPoolService";

    private Binder binderPool = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return binderPool;
    }

}
