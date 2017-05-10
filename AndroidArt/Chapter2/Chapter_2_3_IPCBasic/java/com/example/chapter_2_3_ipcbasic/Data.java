package com.example.chapter_2_3_ipcbasic;

import java.io.Serializable;

/**
 * Created by Guure on 2017/5/10.
 */

/* 实现Serializable */
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
