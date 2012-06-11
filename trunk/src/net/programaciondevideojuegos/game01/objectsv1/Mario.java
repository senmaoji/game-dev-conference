package net.programaciondevideojuegos.game01.objectsv1;

import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Sprite2D;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Mario extends Sprite2D {

	private boolean isAlive;

	public Mario(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		isAlive = true;
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
		super.onDraw(canvas, paint);
		/*
		 * Paint p = new Paint(); p.setARGB(128, 0, 255, 0);
		 * canvas.drawRect(getBounds(), p);
		 */
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
