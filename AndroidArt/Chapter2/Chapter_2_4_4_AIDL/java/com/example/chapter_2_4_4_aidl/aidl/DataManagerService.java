package com.example.chapter_2_4_4_aidl.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.chapter_2_4_4_aidl.aidl.Data;
import com.example.chapter_2_4_4_aidl.aidl.IDataManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Guure on 2017/5/11.
 */

public class DataManagerService extends Service {

    private AtomicBoolean isServiceDestroyed = new AtomicBoolean(false);

    /* CopyOnWriteArrayList支持并发读/写 */
    private CopyOnWriteArrayList<Data> dataList = new CopyOnWriteArrayList<Data>();
//    private CopyOnWriteArrayList<IOnNewDataArrivedListener> listenerList = new CopyOnWriteArrayList<IOnNewDataArrivedListener>();
    private RemoteCallbackList<IOnNewDataArrivedListener> listenerList = new RemoteCallbackList<IOnNewDataArrivedListener>();

    private Binder binder = new IDataManager.Stub() {

        @Override
        public List<Data> getDataList() throws RemoteException {
            SystemClock.sleep(5000);
            return dataList;
        }

        @Override
        public void addData(Data data) throws RemoteException {
            dataList.add(data);
        }

        @Override
        public void registerListener(IOnNewDataArrivedListener listener) throws RemoteException {
//            if (!listenerList.contains(listener)) {
//                listenerList.add(listener);
//            } else {
//                Log.d("service", "already exists.");
//            }
//            Log.d("service", "registerListener, size: " + listenerList.size());
            listenerList.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewDataArrivedListener listener) throws RemoteException {
//            if (listenerList.contains(listener)) {
//                listenerList.remove(listener);
//                Log.d("service", "unregister listener succeed.");
//            } else {
//                Log.d("service", "not found, can not unregister.");
//            }
//            Log.d("service", "unregisterListener, current size: " + listenerList.size());
            listenerList.unregister(listener);
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        dataList.add(new Data(13));
        dataList.add(new Data(23));
        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 使用permission验证
        int check = checkCallingOrSelfPermission("com.example.chapter_2_4_4_aidl.ACCESS_DATA_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            Log.d("service", "permission denied");
            return null;
        }
        Log.d("service", "permission accepted");
        return binder;
    }

    @Override
    public void onDestroy() {
        isServiceDestroyed.set(true);
        super.onDestroy();
    }

    private void onNewDataArrived(Data data) throws RemoteException {
//        Log.d("service", "onNewDataArrived, notify listeners: " +listenerList.size());
//        for (int i = 0; i < listenerList.size(); i++) {
//            IOnNewDataArrivedListener listener = listenerList.get(i);
//            Log.d("service", "onNewDataArrived, notify listener: " + listener);
//            listener.onNewDataArrived(data);
//        }
        dataList.add(data);
        // 遍历RemoteCallbackList的一般写法
        final int N = listenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewDataArrivedListener l = listenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewDataArrived(data);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        listenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            // do background processing here ....
            while (!isServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int dataId = dataList.size() + 100;
                Data newData = new Data(dataId);
                try {
                    onNewDataArrived(newData);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
