/*

	in AndroidManifest.xml:

	<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

	<application>
		...
    <receiver android:name=".BootCompleteReceiver" >
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED" />
        </intent-filter>
    </receiver>

  For static registration.

*/

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "开机啦！", Toast.LENGTH_SHORT).show();
    }
}