package com.example.administrator.lightsensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    private SensorManager sensorManager1;

    private TextView lightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager1 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor1 = sensorManager1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager1.registerListener(listener1, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
        if (sensorManager1 != null) {
            sensorManager1.unregisterListener(listener1);
        }
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // values数组中第一个下标的值就是当前的光照强度
            float value = sensorEvent.values[0];
            lightLevel.setText("Current light level is " + value + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private SensorEventListener listener1 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // 加速度可能会是负值，所以要取它们的绝对值
            float xValue = Math.abs(sensorEvent.values[0]);
            float yValue = Math.abs(sensorEvent.values[1]);
            float zValue = Math.abs(sensorEvent.values[2]);
            if (xValue > 15 || yValue > 15 || zValue > 15) {
                // 认为用户摇动了手机，触发摇一摇逻辑
                Toast.makeText(MainActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

}
