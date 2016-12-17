public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                Uri soundUri = Uri.fromFile(new File("/storage/emulated/0/netease/cloudmusic/Music/Jibbs - Chain Hang Low.mp3"));
                long[] vibrates = {0, 1000, 1000, 1000};
                Notification notification = new Notification.Builder(this)
                        .setContentTitle("This is a content title")
                        .setContentText("This is a content text")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pi)
//                        .setSound(soundUri)
                        .setVibrate(vibrates)
                        .setLights(Color.GREEN, 1000, 1000) // not working
                        .setPriority(Notification.PRIORITY_MAX)
                        .setOngoing(true)
                        .build();
//                notification.ledARGB = Color.GREEN;
//                notification.ledOnMS = 1000;
//                notification.ledOffMS = 1000;
//                notification.flags = Notification.FLAG_SHOW_LIGHTS;
//                notification.defaults = Notification.DEFAULT_LIGHTS;
                try {
                    Thread.currentThread();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                manager.notify(1, notification);
            default:
                break;
        }
    }
}
