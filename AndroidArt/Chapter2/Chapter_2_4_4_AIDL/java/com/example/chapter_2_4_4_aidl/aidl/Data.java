package com.example.chapter_2_4_4_aidl.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guure on 2017/5/11.
 */

public class Data implements Parcelable {

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Data(int num) {
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

    protected Data(Parcel in) {
        this.num = in.readInt();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

}
