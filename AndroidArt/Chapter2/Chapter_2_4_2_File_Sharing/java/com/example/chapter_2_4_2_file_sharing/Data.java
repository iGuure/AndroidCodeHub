package com.example.chapter_2_4_2_file_sharing;

import java.io.Serializable;

/**
 * Created by Guure on 2017/5/11.
 */

public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    public Data(int num) {
        this.num = num;
    }

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
