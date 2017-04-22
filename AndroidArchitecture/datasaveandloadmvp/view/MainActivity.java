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

public class MainActivity extends AppCompatActivity implements IMainActivity, View.OnClickListener {

    private DataPresenter mDataPresenter;

    private EditText editTextId;
    private EditText editTextFirstName;
    private EditText editTextLastName;

    private Button buttonSave;
    private Button buttonLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataPresenter = new DataPresenter(this);

        editTextId = (EditText) findViewById(R.id.id);
        editTextFirstName = (EditText) findViewById(R.id.firstName);
        editTextLastName = (EditText) findViewById(R.id.lastName);

        buttonSave = (Button) findViewById(R.id.save);
        buttonLoad = (Button) findViewById(R.id.load);

        buttonSave.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                Log.d("Guu", "click");
                mDataPresenter.save();
                break;
            case R.id.load:
                mDataPresenter.load();
                break;
            default:
                break;
        }
    }

}
