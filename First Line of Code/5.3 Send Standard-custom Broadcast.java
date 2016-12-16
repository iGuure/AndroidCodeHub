public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        // 如果接收有序广播，可以使用abortBroadcast()截断广播
        // abortBroadcast();
    }
}

/*

	静态注册广播接收器
	可以使用<intent-filter android:priority="100">规定广播接收器的优先级，默认为0（参考API文档）
  <receiver android:name=".MyBroadcastReceiver">
      <intent-filter>
      		自定义广播
          <action android:name="com.example.broadcasttest.MY_BROADCAST" />
      </intent-filter>
  </receiver>

  <Button
    android:id="@+id/button"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Send Broadcast"/>

*/

Button button = (Button) findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    		// 发送自定义广播
        Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
        sendBroadcast(intent);
        // 发送有序广播
        // sendOrderedBroadcast(intent, null);
    }
});