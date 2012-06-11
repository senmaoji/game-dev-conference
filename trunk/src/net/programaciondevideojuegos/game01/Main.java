package net.programaciondevideojuegos.game01;

import net.programaciondevideojuegos.game01.utils.SoundManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class Main extends Activity {

	public int defaultOrientation = 0;
	public WakeLock wakeLock = null;
	public Display display = null;
	public SharedPreferences sharedPrefs = null;
	private Vibrator vibrator = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		// 0 = Samsung Galaxy Tab 10.1 ; 1 = Normal Devices
		defaultOrientation = display.getOrientation();

		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				getResources().getString(R.string.wake_lock));

		setFullScreen();
	}

	public void setFullScreen() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public void killProcess() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.enter_transition,
				R.anim.exit_transition);

	}

	public void startVibration(long milliseconds) {
		if (vibrator != null) {
			vibrator.cancel();
			vibrator.vibrate(milliseconds);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			SoundManager.resumeBackgroundMusic();
			if (wakeLock != null)
				wakeLock.acquire();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SoundManager.pauseBackgroundMusic();
		if (wakeLock != null)
			wakeLock.release();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SoundManager.releaseMusic();
		System.gc();
	}

}