package com.example.datasaveandloadmvp.model;

import android.util.SparseArray;

import com.example.datasaveandloadmvp.data.Data;

/**
 * Created by Guure on 2017/4/22.
 */

public class DataModel implements IDataModel {

    private SparseArray<Data> array = new SparseArray<Data>();

    @Override
    public void save(int id, Data data) {
        array.put(id, data);
    }

    @Override
    public Data load(int id) {
        if (array.indexOfKey(id) >= 0)
            return array.get(id);
        return null;
    }
}
