package net.programaciondevideojuegos.game01.activities;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.engine.GameEngine2D;
import net.programaciondevideojuegos.game01.task.UploadScoreTask;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.SoundManager;
import net.programaciondevideojuegos.game01.utils.Util;
import android.app.Dialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class GameScene extends Main implements SensorEventListener {

	private GameEngine2D gameEngine2D = null;
	private SensorManager sensorManager = null;
	private Dialog dialog = null;
	private int level = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// gameEngine2D = new GameEngine2D(this);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		createDialog();
		loadLevel();
		gameEngine2D = new GameEngine2D(this, Integer.parseInt(sharedPrefs
				.getString("character", "0")));
		setContentView(gameEngine2D);
		SoundManager.playBackgroundMusic(getApplicationContext(),
				Assets.sound_background);
	}

	private void loadLevel() {
		level = Integer.parseInt(sharedPrefs.getString("difficulty", "0"));
		switch (level) {
		case 0:
			Assets.LEVEL = getResources().getString(R.string.easy);
			Assets.BANANA_SPEED = 2;
			Assets.BANANA_INTERVAL = 5;
			Assets.HOLE_SPEED = 1;
			Assets.HOLE_INTERVAL = 5;
			break;
		case 1:
			Assets.LEVEL = getResources().getString(R.string.normal);
			Assets.BANANA_SPEED = 3;
			Assets.BANANA_INTERVAL = 4;
			Assets.HOLE_SPEED = 2;
			Assets.HOLE_INTERVAL = 4;
			break;
		case 2:
			Assets.LEVEL = getResources().getString(R.string.hard);
			Assets.BANANA_SPEED = 4;
			Assets.BANANA_INTERVAL = 3;
			Assets.HOLE_SPEED = 3;
			Assets.HOLE_INTERVAL = 3;
			break;
		default:
			level = 0;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				if (gameEngine2D != null && gameEngine2D.getMario() != null
						&& gameEngine2D.getMario().isAlive()
						&& gameEngine2D.isReady()) {
					float vx = event.values[0] * -1;
					float vy = event.values[1];

					if (defaultOrientation == 1) {
						vx = event.values[1];
						vy = event.values[0];
					}
					gameEngine2D.getMario().move(vx, vy);
				}
				break;
			}
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if (sensorManager != null)
				sensorManager.registerListener(this, sensorManager
						.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
						SensorManager.SENSOR_DELAY_GAME);
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		_stopSensor();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		_stopSensor();
		if (gameEngine2D != null) {
			if (gameEngine2D.getThread() != null) {
				gameEngine2D.getThread().setPause(true);
				gameEngine2D.getThread().setRunning(false);
			}
			gameEngine2D.releaseMemory();
		}
		gameEngine2D = null;
		sensorManager = null;
		if (dialog != null)
			dialog.dismiss();
		SoundManager.releaseSounds();
	}

	public void runDialog() {
		if (dialog != null) {
			SoundManager.releaseMusic();
			SoundManager
					.playSFX(getApplicationContext(), Assets.sound_gameover);
			startVibration(500);

			TextView tv = null;

			tv = (EditText) dialog.findViewById(R.id.tvPlayer);
			tv.setText(sharedPrefs.getString("nickname", getResources()
					.getString(R.string.player)));

			tv = (TextView) dialog.findViewById(R.id.tvScore);
			tv.setText(Assets.SCORE + "");

			this.runOnUiThread(new Runnable() {
				public void run() {
					dialog.show();
				};
			});
		}
	}

	private void _stopSensor() {
		if (sensorManager != null)
			sensorManager.unregisterListener(this);
	}

	private void createDialog() {
		dialog = new Dialog(GameScene.this,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		/*
		 * dialog.getWindow().setFlags(
		 * WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		 * WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		 */

		LayoutInflater inflater = LayoutInflater.from(GameScene.this);
		View view = inflater.inflate(R.layout.dialog_endgame, null);

		dialog.setContentView(view);
		dialog.setCancelable(false);

		final View btnSend = view.findViewById(R.id.btnSend);
		final View btnClose = view.findViewById(R.id.btnClose);

		btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View clicked) {
				if (gameEngine2D != null && gameEngine2D.getThread() != null) {
					if (Util.isNetworkAvailable(GameScene.this)) {
						btnSend.setEnabled(false);
						EditText tv = (EditText) dialog
								.findViewById(R.id.tvPlayer);
						String player = tv.getText().toString().trim();
						if (player.length() == 0) {
							player = getResources().getString(R.string.player);
						}
						UploadScoreTask task = new UploadScoreTask(
								GameScene.this, player, level + "");
						task.execute();
					} else {
						makeToast(GameScene.this,
								getResources().getString(R.string.noConnection));
					}
				}
			}
		});

		btnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View clicked) {
				if (gameEngine2D != null && gameEngine2D.getThread() != null) {
					gameEngine2D.surfaceDestroyed(gameEngine2D.getHolder());
					GameScene.this.finish();
				}
			}
		});
	}

}
