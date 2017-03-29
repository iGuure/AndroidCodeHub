package com.example.handlermemoryleaks;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    public boolean outter = false;

    // Handler匿名内部类写法会导致内存泄漏！
    //
    // 匿名内部类的对象会持有外部类的强引用（即对象handler指向MainActivity的对象）
    // 因此可以访问外部类的成员变量（outter），且当MainActivity的引用被销毁时
    // 因为MainActivity的对象还被对象handler强引用着，因此GC不会回收MainActivity的对象
    // 导致MainActivity的对象及其相关资源无法被回收，即内存已经泄露
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("TAG", "From handler => outter : " + outter);
        }
    };

    // 可以使用静态内部类写法，这样MyHandler不再持有外部类的引用
    //
    // 静态内部类不能访问外部类的成员变量，可以使用WeakReference弱引用类
    // 当一个对象只被弱引用指向时，该对象会被GC回收
    // 通过弱引用即可访问外部类的成员变量
    // 记得在Activity的onDestroy()方法中，调用以下方法：
    // Handler.removeCallbacksAndMessages(null)
    // 该方法可以取消该Handler对象的所有Message和Runnable
    private static class MyHandler extends Handler {

        WeakReference<MainActivity> activityWeakReference;

        MyHandler(MainActivity activity) {
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (activityWeakReference.get() == null)    return;
            Log.d("TAG", "From myHandler => outter : " + activityWeakReference.get().outter);
        }
    }

    Handler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.sendEmptyMessage(1);
        myHandler.sendEmptyMessage(1);

        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // If null, all callbacks and messages will be removed
        myHandler.removeCallbacksAndMessages(null);
    }
}