package com.example.chapter_2_3_ipcbasic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guure on 2017/5/10.
 */

/* 实现Parcelable */
public class Data2 implements Parcelable {

    public Data2(int num) {
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
        dest.writeInt(num);
    }

    public static final Parcelable.Creator<Data2> CREATOR = new Parcelable.Creator<Data2>() {
        @Override
        public Data2 createFromParcel(Parcel source) {
            return new Data2(source);
        }

        @Override
        public Data2[] newArray(int size) {
            return new Data2[size];
        }
    };

    private Data2(Parcel in) {
        num = in.readInt();
    }

}
