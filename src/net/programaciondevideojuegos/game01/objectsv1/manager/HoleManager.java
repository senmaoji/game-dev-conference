package net.programaciondevideojuegos.game01.objectsv1.manager;

import java.util.ArrayList;
import java.util.List;

import net.programaciondevideojuegos.game01.objectsv1.Hole;
import net.programaciondevideojuegos.game01.objectsv1.Player;
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
		Bitmap bmp = Util.decodeBitmap(context.getResources(),
				Assets.asset_hole);

		posX = Util.getRandomNumber(0, Assets.DEFAULT_WIDTH - bmp.getWidth());

		for (int i = 0; i < Assets.MAX_HOLES; i++) {
			if (i > 0)
				setNewXPosition(i - 1);
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
					int last = getLastHoleInMap();
					float lastY = listHoles.get(last).getY()
							+ listHoles.get(i).getHeight() * intervalHoles;
					setNewXPosition(last);
					listHoles.get(i).setX(posX);
					listHoles.get(i).setY(lastY);
				}
			}
		}
	}

	private int getLastHoleInMap() {
		int search = -1;
		if (listHoles != null && !listHoles.isEmpty()) {
			float max = 0;
			for (int i = 0; i < listHoles.size(); i++) {
				if (listHoles.get(i).getY() > max) {
					max = listHoles.get(i).getY();
					search = i;
				}
			}
		}
		return search;
	}

	private void setNewXPosition(int n) {
		if (listHoles.get(n).getX() + listHoles.get(n).getWidth() / 2 < Assets.DEFAULT_WIDTH / 2) {
			posX = Util.getRandomNumber(Assets.DEFAULT_WIDTH / 2
					+ listHoles.get(n).getWidth(), Assets.DEFAULT_WIDTH
					- listHoles.get(n).getWidth());
		} else {
			posX = Util.getRandomNumber(0, Assets.DEFAULT_WIDTH / 2);
		}
	}

	public void clear() {
		if (listHoles != null)
			listHoles.clear();
		listHoles = null;
	}

	public boolean collidesWithMario(Player mario) {
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
