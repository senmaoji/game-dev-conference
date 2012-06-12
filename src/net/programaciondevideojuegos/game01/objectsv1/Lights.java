package net.programaciondevideojuegos.game01.objectsv1;

import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Sprite2D;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Lights extends Sprite2D {

	private int lights[] = null;
	private int currentFrame = 0;
	private byte bucle = 1, MAX_BUCLE = 16;
	private Context context = null;

	public Lights(Context context, int lights[]) {
		super(Util.decodeBitmap(context.getResources(), lights[0]), 0, 0);
		setPosition((Assets.DEFAULT_WIDTH - getWidth()) / 2,
				(Assets.DEFAULT_HEIGHT - getHeight()) / 2);
		// TODO Auto-generated constructor stub
		this.lights = lights;
		this.context = context;
	}

	@Override
	public void setBitmap(Bitmap bitmap) {
		// TODO Auto-generated method stub
		super.setBitmap(bitmap);
		setWidth(bitmap.getWidth());
		setHeight(bitmap.getHeight());
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.onDraw(canvas, paint);
		if (isVisible()) {
			if (bucle % MAX_BUCLE == 0) {
				bucle = 0;
				currentFrame++;
				if (currentFrame > lights.length - 1)
					currentFrame = 0;
				setBitmap(Util.decodeBitmap(context.getResources(), lights[currentFrame]));
			}
			bucle++;
		}
	}
}
