package com.example.datasaveandloadmvp.view;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.datasaveandloadmvp.R;
import com.example.datasaveandloadmvp.model.Data;
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

    }

    @Override
    public void saveData() {
        int id = Integer.parseInt(editTextId.getText().toString());
        String fn = editTextFirstName.getText().toString();
        String ln = editTextLastName.getText().toString();
        mDataPresenter.saveData(new Data(id, fn, ln));
    }

    @Override
    public void loadData() {
        int id = Integer.parseInt(editTextId.getText().toString());

        Data data = mDataPresenter.loadData(id);

        editTextId.setText(data.getId());
        editTextFirstName.setText(data.getFirstName());
        editTextLastName.setText(data.getLastName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                saveData();
                break;
            case R.id.load:
                loadData();
                break;
            default:
                break;
        }
    }

}
