package net.programaciondevideojuegos.game01.objectsv1;

import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Sprite2D;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Player extends Sprite2D {

	private boolean isAlive;
	private Bitmap bmpSmoke = null;
	private int currentAlpha = 0, incAlpha = 35;

	public Player(Bitmap bitmap, float x, float y, Context context) {
		super(bitmap, x, y);
		isAlive = true;
		bmpSmoke = Util
				.decodeBitmap(context.getResources(), Assets.asset_smoke);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(float x, float y) {
		// TODO Auto-generated method stub
		super.move(x, y);

		if (getX() < 0)
			setX(0);
		else if (getX() > Assets.DEFAULT_WIDTH - this.getWidth())
			setX(Assets.DEFAULT_WIDTH - this.getWidth());

		if (getY() < 50)
			setY(50);
		else if (getY() > Assets.DEFAULT_HEIGHT - getHeight() * 2.5f)
			setY(Assets.DEFAULT_HEIGHT - getHeight() * 2.5f);
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub

		if (bmpSmoke != null && isAlive) {
			int bmpX = (int) (getX() - bmpSmoke.getWidth() * 0.25);
			int bmpY = (int) (getY() - bmpSmoke.getHeight() * 0.3);

			Paint paintSmoke = new Paint();
			currentAlpha = currentAlpha + incAlpha;
			if (currentAlpha > 255) {
				currentAlpha = 255;
				incAlpha = -35;
			} else if (currentAlpha < 0) {
				currentAlpha = 0;
				incAlpha = 35;
			}
			paintSmoke.setAlpha(currentAlpha);
			canvas.drawBitmap(bmpSmoke, bmpX, bmpY, paintSmoke);
		}

		super.onDraw(canvas, paint);
		/*
		 * Paint p = new Paint(); p.setARGB(128, 0, 255, 0);
		 * canvas.drawRect(getBounds(), p);
		 */
	}

	@Override
	public RectF getBounds() {
		// TODO Auto-generated method stub
		RectF rectPlayer = new RectF();
		rectPlayer.left = getX() + getWidth() * 0.2f;
		rectPlayer.right = rectPlayer.left + getWidth() * 0.6f;
		rectPlayer.top = getY() + getHeight() * 0.2f;
		rectPlayer.bottom = rectPlayer.top + getHeight() * 0.6f;
		return rectPlayer;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void clear() {
		if (bmpSmoke != null && !bmpSmoke.isRecycled())
			bmpSmoke.recycle();
		bmpSmoke = null;
	}

}
