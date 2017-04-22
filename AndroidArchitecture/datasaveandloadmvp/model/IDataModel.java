package com.example.datasaveandloadmvp.model;

import com.example.datasaveandloadmvp.data.Data;

/**
 * Created by Guure on 2017/4/22.
 */

public interface IDataModel {

    public void save(int id, Data data);

    public Data load(int id);

}
