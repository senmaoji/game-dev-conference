package net.programaciondevideojuegos.game01.engine;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	private final int FPS = 1000 / 24;
	private boolean isRunning = false, isPause = true;
	private SurfaceHolder holder = null;
	private GameEngine2D gameEngine = null;

	public GameThread(SurfaceHolder holder, GameEngine2D gameEngine) {
		this.holder = holder;
		this.gameEngine = gameEngine;
		this.isRunning = true;
		this.isPause = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Canvas canvas = null;
		while (isRunning && gameEngine != null) {
			if (!isPause) {
				try {
					canvas = holder.lockCanvas();
					synchronized (holder) {
						gameEngine.update();
						gameEngine.onDraw(canvas);
						try {
							Thread.sleep(FPS);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							Log.d("Fail: ", e.getMessage());
						}
					}
				} finally {
					if (canvas != null) {
						holder.unlockCanvasAndPost(canvas);
					}
				}
			}

		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

}
