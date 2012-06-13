package net.programaciondevideojuegos.game01.objectsv1.manager;

import net.programaciondevideojuegos.game01.utils.Assets;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class TrackManager {

	private RectF tracks[] = new RectF[3];
	private final int width = 10, height = 125;
	private int speed;

	public TrackManager() {
		speed = Assets.HOLE_SPEED;
		float x = (Assets.DEFAULT_WIDTH - width) / 2;
		float y = 50;
		for (int i = 0; i < tracks.length; i++) {
			tracks[i] = new RectF(x, y, x + width, y + height);
			y += height * 1.5;
		}
	}

	public void onDraw(Canvas canvas) {
		if (tracks != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			for (int i = 0; i < tracks.length; i++) {
				canvas.drawRect(tracks[i], paint);
			}
		}

	}

	public void update() {
		if (tracks != null) {
			for (int i = 0; i < tracks.length; i++) {
				tracks[i].top -= speed;
				tracks[i].bottom = tracks[i].top + height;
				if (tracks[i].bottom < 50) {
					tracks[i].top = Assets.DEFAULT_HEIGHT;
					tracks[i].bottom = tracks[i].top + height;
				}
			}
		}
	}

	public void clear() {
		tracks = null;
	}

	public void incrementSpeed() {
		speed++;
	}
}
