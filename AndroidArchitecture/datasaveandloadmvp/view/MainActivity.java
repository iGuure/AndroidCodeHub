package com.example.datasaveandloadmvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.datasaveandloadmvp.R;
import com.example.datasaveandloadmvp.data.Data;
import com.example.datasaveandloadmvp.presenter.DataPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private DataPresenter mDataPresenter;

    @BindView(R.id.id) EditText editTextId;
    @BindView(R.id.firstName) EditText editTextFirstName;
    @BindView(R.id.lastName) EditText editTextLastName;

    @BindView(R.id.save) Button buttonSave;
    @BindView(R.id.load) Button buttonLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDataPresenter = new DataPresenter(this);
    }

    @OnClick(R.id.save) void save() {
        mDataPresenter.save();
    }

    @OnClick(R.id.load) void load() {
        mDataPresenter.load();
    }

    @Override
    public int getId() {
        return Integer.parseInt(editTextId.getText().toString());
    }

    @Override
    public String getFirstName() {
        return editTextFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return editTextLastName.getText().toString();
    }

    @Override
    public void setFirstName(String fn) {
        editTextFirstName.setText(fn);
    }

    @Override
    public void setLastName(String ln) {
        editTextLastName.setText(ln);
    }

}
