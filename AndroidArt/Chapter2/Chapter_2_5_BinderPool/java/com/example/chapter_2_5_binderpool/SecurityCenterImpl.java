package com.example.chapter_2_5_binderpool;

import android.os.RemoteException;

import com.example.chapter_2_5_binderpool.aidl.ISecurityCenter;

/**
 * Created by Guure on 2017/5/15.
 */

public class SecurityCenterImpl extends ISecurityCenter.Stub {

    private static final char SECURE_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECURE_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
