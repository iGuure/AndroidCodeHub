// IDataManager.aidl
package com.example.chapter_2_4_4_aidl.aidl;

import com.example.chapter_2_4_4_aidl.aidl.Data;
import com.example.chapter_2_4_4_aidl.aidl.IOnNewDataArrivedListener;

interface IDataManager {
    List<Data> getDataList();
    void addData(in Data data);
    void registerListener(IOnNewDataArrivedListener listener);
    void unregisterListener(IOnNewDataArrivedListener listener);
}