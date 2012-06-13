package net.programaciondevideojuegos.game01.objectsv1;

import net.programaciondevideojuegos.game01.utils.Sprite2D;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Rock extends Sprite2D {

	public Rock(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.onDraw(canvas, paint);
		/*
		 * Paint p = new Paint(); p.setARGB(128, 255, 0, 0);
		 * canvas.drawRect(getBounds(), p);
		 */

	}

	@Override
	public RectF getBounds() {
		// TODO Auto-generated method stub
		RectF rectPlayer = new RectF();
		rectPlayer.left = getX() + getWidth() * 0.1f;
		rectPlayer.right = rectPlayer.left + getWidth() * 0.8f;
		rectPlayer.top = getY() + getHeight() * 0.1f;
		rectPlayer.bottom = rectPlayer.top + getHeight() * 0.8f;
		return rectPlayer;
	}

}
