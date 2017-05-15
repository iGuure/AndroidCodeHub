package com.example.chapter_2_5_binderpool;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.chapter_2_5_binderpool.aidl.ICompute;
import com.example.chapter_2_5_binderpool.aidl.ISecurityCenter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ISecurityCenter securityCenter;
    private ICompute compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(MainActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        securityCenter = SecurityCenterImpl.asInterface(securityBinder);
        Log.d(TAG, "visit ISecurityCenter");
        String msg = "helloworld-安卓";
        System.out.println("content: " + msg);
        try {
            String password = securityCenter.encrypt(msg);
            System.out.println("encrypt: " + password);
            System.out.println("decrypt: " + securityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        compute = ComputeImpl.asInterface(computeBinder);
        try {
            System.out.println("3 + 5 = " + compute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
