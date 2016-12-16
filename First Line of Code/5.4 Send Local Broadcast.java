private LocalReceiver localReceiver;

private LocalBroadcastManager localBroadcastManager;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 获得实例
    localBroadcastManager = localBroadcastManager.getInstance(this);

    Button button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
            localBroadcastManager.sendBroadcast(intent1); // 发送本地广播
        }
    });

    intentFilter = new IntentFilter();
    intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
    localReceiver = new LocalReceiver();
    localBroadcastManager.registerReceiver(localReceiver, intentFilter); // 注册本地广播监听器
}

@Override
protected void onDestroy() {
    super.onDestroy();
    localBroadcastManager.unregisterReceiver(localReceiver);
}

class LocalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
    }
}