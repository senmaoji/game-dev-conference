package net.programaciondevideojuegos.game01.utils;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class SoundManager {

	public static MediaPlayer backgroundMusic = null;
	public static SoundPool soundPool = null;

	public static void playBackgroundMusic(Context context, String assetSound) {
		if (assetSound.length() > 0) {
			MediaPlayer mp = new MediaPlayer();
			try {
				AssetManager assetManager = context.getAssets();
				AssetFileDescriptor descriptor = assetManager
						.openFd(assetSound);
				mp.setDataSource(descriptor.getFileDescriptor(),
						descriptor.getStartOffset(), descriptor.getLength());
				mp.prepare();
				mp.setLooping(true);
			} catch (IOException e) {
				mp = null;
			}
			if (mp != null) {
				backgroundMusic = mp;
				mp.start();
			}
		}
	}

	public static void playSFX(Context context, String assetSound) {
		if (soundPool != null) {
			soundPool.release();
			soundPool = null;
		}
		if (assetSound.length() > 0) {
			int soundID = -1;
			soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
			try {
				AssetManager assetManager = context.getAssets();
				AssetFileDescriptor descriptor = assetManager
						.openFd(assetSound);
				soundID = soundPool.load(descriptor, 1);

			} catch (IOException e) {
			}
			if (soundID != -1) {
				soundPool
						.setOnLoadCompleteListener(new OnLoadCompleteListener() {

							@Override
							public void onLoadComplete(SoundPool soundPool,
									int sampleId, int status) {
								// TODO Auto-generated method stub
								soundPool.play(sampleId, 1, 1, 1, 0, 1);
							}
						});
			}
		}
	}

	public static void releaseMusic() {
		if (backgroundMusic != null)
			backgroundMusic.release();
		backgroundMusic = null;
	}

	public static void releaseSFX() {
		if (soundPool != null)
			soundPool.release();
		soundPool = null;
	}

	public static void pauseBackgroundMusic() {
		if (backgroundMusic != null && backgroundMusic.isPlaying())
			backgroundMusic.pause();
	}

	public static void resumeBackgroundMusic() {
		if (backgroundMusic != null && !backgroundMusic.isPlaying())
			backgroundMusic.start();
	}

	public static void releaseSounds() {
		releaseMusic();
		releaseSFX();
	}

}
