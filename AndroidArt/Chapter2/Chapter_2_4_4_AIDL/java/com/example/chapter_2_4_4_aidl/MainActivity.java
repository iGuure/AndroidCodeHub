package com.example.chapter_2_4_4_aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chapter_2_4_4_aidl.aidl.Data;
import com.example.chapter_2_4_4_aidl.aidl.DataManagerService;
import com.example.chapter_2_4_4_aidl.aidl.IDataManager;
import com.example.chapter_2_4_4_aidl.aidl.IOnNewDataArrivedListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button getBookListButton;

    private static final int MESSAGE_NEW_DATA_ARRIVED = 1;

    private IDataManager dataManager;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_DATA_ARRIVED:
                    Log.d("client", "receive new data: " + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            dataManager = IDataManager.Stub.asInterface(service);
            try {
                List<Data> list = dataManager.getDataList();
                Log.d("client", "query data list, list type: " + list.getClass().getCanonicalName());
                Log.d("client", "query data list: " + list.toString());

                Data newData = new Data(30);
                dataManager.addData(newData);
                Log.d("client", "add data: " + newData);
                List<Data> newList = dataManager.getDataList();
                Log.d("client", "query data list: " + newList.toString());
                dataManager.registerListener(onNewDataArrivedListener);
            } catch (RemoteException e) {
               e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataManager = null;
            Log.d("client", "binder died");
        }

    };

    private IOnNewDataArrivedListener onNewDataArrivedListener = new IOnNewDataArrivedListener.Stub() {
        @Override
        public void onNewDataArrived(Data newData) throws RemoteException {
            handler.obtainMessage(MESSAGE_NEW_DATA_ARRIVED, newData).sendToTarget();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBookListButton = (Button) findViewById(R.id.getBookList);
        // 由于getDataList方法是耗时操作，所以连续单击几次客户端就会ANR
        getBookListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (dataManager != null) {
                            try {
                                dataManager.getDataList();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        Intent intent = new Intent(this, DataManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (dataManager != null && dataManager.asBinder().isBinderAlive()) {
            try {
                Log.d("client", "unregister listener: " + onNewDataArrivedListener);
                dataManager.unregisterListener(onNewDataArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }

}
