package com.example.chapter_2_5_binderpool;

import android.os.RemoteException;

import com.example.chapter_2_5_binderpool.aidl.ICompute;

/**
 * Created by Guure on 2017/5/15.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
