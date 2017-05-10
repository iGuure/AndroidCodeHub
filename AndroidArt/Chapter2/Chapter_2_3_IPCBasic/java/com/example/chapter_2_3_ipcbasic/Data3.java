package com.example.chapter_2_3_ipcbasic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guure on 2017/5/10.
 */

/* 实现Binder的实体类 */
public class Data3 implements Parcelable {

    public Data3(int num) {
        this.num = num;
    }

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.num);
    }

    protected Data3(Parcel in) {
        this.num = in.readInt();
    }

    public static final Parcelable.Creator<Data3> CREATOR = new Parcelable.Creator<Data3>() {
        @Override
        public Data3 createFromParcel(Parcel source) {
            return new Data3(source);
        }

        @Override
        public Data3[] newArray(int size) {
            return new Data3[size];
        }
    };

}
