package net.programaciondevideojuegos.game01.objectsv1;

import net.programaciondevideojuegos.game01.utils.Sprite2D;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Banana extends Sprite2D {

	public Banana(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		// TODO Auto-generated constructor stub
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

}
