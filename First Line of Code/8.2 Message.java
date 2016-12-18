public class MainActivity extends Activity {

    private TextView sender;

    private TextView content;

    private IntentFilter intentFilter;

    private MessageReceiver messageReceiver;

    private EditText to;

    private EditText msgInput;

    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);

        //设置过滤器
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        //获取MessageReceiver实例
        messageReceiver = new MessageReceiver();

        //注册广播
        registerReceiver(messageReceiver,intentFilter);

        to = (EditText) findViewById(R.id.to);
        msgInput = (EditText) findViewById(R.id.msg_input);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(to.getText().toString(), null, msgInput.getText().toString(), null, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
    }

    //接收短信广播接收器
    class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("Guure", "已收到！");

            //使用 pdu 密钥来提取一个 SMS pdus 数组 ,其中每一个 pdu 都表示一条短信消息
            Object[] smsPdus = (Object[]) intent.getExtras().get("pdus");

            //用来储存消息
            SmsMessage[] smsMessage = new SmsMessage[smsPdus.length];

            /**
             * SmsMessage.createFromPdu((byte[]) smsPdus[i], format);
             * 第二个参数应该是短信的类型, GSM与CDMA短信的解码方式不同,所以应该传入这个参数
             * */
            String format = intent.getStringExtra("format");

            //将每一个 pdu 格式数组转换为 SmsMessage 对象
            for(int i = 0; i < smsMessage.length; i++){
                if(Build.VERSION.SDK_INT < 23) {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) smsPdus[i]);
                } else {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) smsPdus[i], format);
                }
            }

            //获取发送的号码
            String address = smsMessage[0].getOriginatingAddress();

            //将多段内容链接起来
            StringBuilder messageContent = new StringBuilder();
            for(SmsMessage message : smsMessage){
                messageContent.append(message.getMessageBody());
            }

            //设置视图内容
            sender.setText(address);
            content.setText(messageContent.toString());
        }
    }
}