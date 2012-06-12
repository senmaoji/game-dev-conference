package net.programaciondevideojuegos.game01.objectsv1.manager;

import java.util.ArrayList;
import java.util.List;

import net.programaciondevideojuegos.game01.objectsv1.Hole;
import net.programaciondevideojuegos.game01.objectsv1.Mario;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class HoleManager {

	private List<Hole> listHoles = null;
	private float posY = 350, posX = 0;
	private int speed, intervalHoles;

	public HoleManager(Context context) {
		listHoles = new ArrayList<Hole>();
		speed = Assets.HOLE_SPEED;
		intervalHoles = Assets.HOLE_INTERVAL;
		Bitmap bmp = Util.decodeBitmap(context, Assets.asset_hole);
		for (int i = 0; i < Assets.MAX_HOLES; i++) {
			posX = Util.getRandomNumber(0,
					Assets.DEFAULT_WIDTH - bmp.getWidth());
			Hole hole = new Hole(bmp, posX, posY);
			posY += bmp.getHeight() * intervalHoles;
			listHoles.add(hole);
		}
	}

	public void onDraw(Canvas canvas, Paint paint) {
		if (listHoles != null && !listHoles.isEmpty()) {
			for (int i = 0; i < listHoles.size(); i++) {
				if (listHoles.get(i) != null)
					listHoles.get(i).onDraw(canvas, paint);
			}
		}
	}

	public void update() {
		if (listHoles != null && !listHoles.isEmpty()) {
			for (int i = 0; i < listHoles.size(); i++) {
				listHoles.get(i).move(0, -speed);
				if (listHoles.get(i).getY() < -listHoles.get(i).getHeight()) {
					float lastY = getYHoleInMap()
							+ listHoles.get(i).getHeight() * intervalHoles;
					listHoles.get(i).setX(
							Util.getRandomNumber(0, Assets.DEFAULT_WIDTH
									- listHoles.get(i).getWidth()));
					listHoles.get(i).setY(lastY);
				}
			}
		}
	}

	private float getYHoleInMap() {
		if (listHoles != null && !listHoles.isEmpty()) {
			float max = 0;
			for (int i = 0; i < listHoles.size(); i++) {
				if (listHoles.get(i).getY() > max)
					max = listHoles.get(i).getY();
			}
			return max;
		}
		return 0.0f;
	}

	public void clear() {
		if (listHoles != null)
			listHoles.clear();
		listHoles = null;
	}

	public boolean collidesWithMario(Mario mario) {
		if (listHoles != null && !listHoles.isEmpty()) {
			for (int i = 0; i < listHoles.size(); i++) {
				if (listHoles.get(i).isCollide(mario)) {
					return true;
				}
			}
		}
		return false;
	}

	public void incrementSpeed() {
		speed++;
	}

}
