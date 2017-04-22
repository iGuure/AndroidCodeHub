package com.example.datasaveandloadmvp.presenter;

import com.example.datasaveandloadmvp.data.Data;
import com.example.datasaveandloadmvp.model.DataModel;
import com.example.datasaveandloadmvp.view.IMainActivity;

/**
 * Created by Guure on 2017/4/22.
 */

public class DataPresenter {

    private DataModel mDataModel;
    private IMainActivity mMainActivity;

    public DataPresenter(IMainActivity mainActivity) {
        mMainActivity = mainActivity;
        mDataModel = new DataModel();
    }

    public void save() {
        mDataModel.save(mMainActivity.getId(), new Data(mMainActivity.getFirstName(), mMainActivity.getLastName()));
    }

    public void load() {
        int id = mMainActivity.getId();
        Data data = mDataModel.load(id);
        mMainActivity.setFirstName(data.getFirstName());
        mMainActivity.setLastName(data.getLastName());
    }

}
