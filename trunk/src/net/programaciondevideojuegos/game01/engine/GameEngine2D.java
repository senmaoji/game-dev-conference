package net.programaciondevideojuegos.game01.engine;

import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.activities.GameScene;
import net.programaciondevideojuegos.game01.objectsv1.Lights;
import net.programaciondevideojuegos.game01.objectsv1.Mario;
import net.programaciondevideojuegos.game01.objectsv1.manager.BananaManager;
import net.programaciondevideojuegos.game01.objectsv1.manager.HoleManager;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameEngine2D extends SurfaceView implements SurfaceHolder.Callback {

	private final long START_IN = 3000L;
	private byte bucle = 1, MAX_BUCLE = 3;
	private Mario mario = null;
	private Context context = null;
	private GameThread thread = null;
	private Bitmap background = null;
	private BananaManager bananaManager = null;
	private HoleManager holeManager = null;
	private Lights lights = null;
	private long currentTime = 0;
	private boolean isReady = false;
	private int characterID = 0;

	public GameEngine2D(Context context, int characterID) {
		super(context);
		this.context = context;
		this.characterID = characterID;
		getHolder().addCallback(this);
		thread = new GameThread(getHolder(), this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		_startGame();
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		thread.setPause(true);
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
		releaseMemory();
	}

	private void _startGame() {
		Assets.SCORE = 0;
		isReady = false;
		Bitmap bmp = null;

		int character = Assets.asset_kart;
		if (characterID == 1)
			character = Assets.asset_usmp;

		bmp = Util.decodeBitmap(context.getResources(), character);

		mario = new Mario(bmp, (Assets.DEFAULT_WIDTH - bmp.getWidth()) / 2, 50);

		bananaManager = new BananaManager(context);
		holeManager = new HoleManager(context);
		lights = new Lights(context, Assets.asset_lights);

		background = Util.decodeBitmap(context.getResources(),
				Assets.asset_background);
		currentTime = System.currentTimeMillis();
	}

	public void update() {

		if (!isReady && currentTime > 0
				&& System.currentTimeMillis() - currentTime > START_IN) {
			((GameScene) context).startVibration(250);
			isReady = true;
		}

		if (isReady) {
			if (Assets.SCORE % Assets.SCORE_FOR_NEXT_LEVEL == 0
					&& bananaManager != null && holeManager != null) {
				Assets.SCORE++;
				bananaManager.incrementSpeed();
				holeManager.incrementSpeed();
			}

			if (bucle % MAX_BUCLE == 0 && mario != null && mario.isAlive()) {
				Assets.SCORE++;
				bucle = 0;
			}
			bucle++;

			if (bananaManager != null && mario != null && mario.isAlive()) {
				bananaManager.update();
				if (bananaManager.collidesWithMario(mario)) {
					mario.setAlive(false);
					((GameScene) context).runDialog();
				}
			}

			if (holeManager != null && mario != null && mario.isAlive()) {
				holeManager.update();
				if (holeManager.collidesWithMario(mario)) {
					mario.setAlive(false);
					((GameScene) context).runDialog();
				}
			}
		}

	}

	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(0xFF000000);
		canvas.scale(Assets.SCALEX, Assets.SCALEY);

		if (background != null)
			canvas.drawBitmap(background, 0, 0, null);

		if (mario != null)
			mario.onDraw(canvas, null);

		if (holeManager != null)
			holeManager.onDraw(canvas, null);

		if (bananaManager != null)
			bananaManager.onDraw(canvas, null);

		if (lights != null && !isReady)
			lights.onDraw(canvas, null);

		// ############################################
		Rect rect = new Rect(0, 0, Assets.DEFAULT_WIDTH, 50);
		Paint paintRect = new Paint();
		paintRect.setARGB(255, 0, 0, 0);
		canvas.drawRect(rect, paintRect);
		paintRect = new Paint();
		paintRect.setARGB(255, 255, 255, 255);
		paintRect.setTextSize(14f);
		canvas.drawText(context.getResources().getString(R.string.score) + ": "
				+ Assets.SCORE, 10, 20, paintRect);
		canvas.drawText(context.getResources().getString(R.string.level) + ": "
				+ Assets.LEVEL, 10, 35, paintRect);
		// ############################################
	}

	public void releaseMemory() {
		if (background != null && !background.isRecycled())
			background.recycle();
		background = null;

		if (bananaManager != null)
			bananaManager.clear();
		bananaManager = null;

		if (holeManager != null)
			holeManager.clear();
		holeManager = null;

		mario = null;
		lights = null;
	}

	public Mario getMario() {
		return mario;
	}

	public GameThread getThread() {
		return thread;
	}

	public boolean isReady() {
		return isReady;
	}

}
