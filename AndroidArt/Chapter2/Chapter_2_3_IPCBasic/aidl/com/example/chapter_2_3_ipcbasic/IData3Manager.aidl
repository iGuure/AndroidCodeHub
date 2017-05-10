// IData3Manager.aidl
// 固定写法，参考即可
package com.example.chapter_2_3_ipcbasic;

// 注意：即使在同一个包中也要导入，这是AIDL的特殊之处
import com.example.chapter_2_3_ipcbasic.Data3;
interface IData3Manager {
    List<Data3> getData3List();
    void addData3(in Data3 data3);
}