// IOnNewDataArrivedListener.aidl
package com.example.chapter_2_4_4_aidl.aidl;

import com.example.chapter_2_4_4_aidl.aidl.Data;

interface IOnNewDataArrivedListener {
    void onNewDataArrived(in Data newData);
}