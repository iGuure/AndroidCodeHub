package com.example.datasaveandloadmvp.presenter;

import com.example.datasaveandloadmvp.model.Data;
import com.example.datasaveandloadmvp.view.IMainActivity;

/**
 * Created by Guure on 2017/4/22.
 */

public class DataPresenter {

    private Data mData;
    private IMainActivity mMainActivity;

    public DataPresenter(IMainActivity mainActivity) {
        mMainActivity = mainActivity;
        mData = new Data();
    }

    public void saveData(Data data) {
        mData = data;
        // TODO: 将mData储存到本地

    }

    public Data loadData(int id) {
        // TODO: 加载本地中ID为id的数据

        return new Data();
    }

}
