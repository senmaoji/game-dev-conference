package net.programaciondevideojuegos.game01.objectsv1.manager;

import java.util.ArrayList;
import java.util.List;

import net.programaciondevideojuegos.game01.objectsv1.Rock;
import net.programaciondevideojuegos.game01.objectsv1.Player;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class RockManager {

	private List<Rock> listRocks = null;
	private float posY = 350, posX = 0;
	private int speed, intervalHoles;

	public RockManager(Context context) {
		listRocks = new ArrayList<Rock>();
		speed = Assets.HOLE_SPEED;
		intervalHoles = Assets.HOLE_INTERVAL;
		Bitmap bmp = Util.decodeBitmap(context.getResources(),
				Assets.asset_rock);

		posX = Util.getRandomNumber(0, Assets.DEFAULT_WIDTH - bmp.getWidth());

		for (int i = 0; i < Assets.MAX_HOLES; i++) {
			if (i > 0)
				setNewXPosition(i - 1);
			Rock hole = new Rock(bmp, posX, posY);
			posY += bmp.getHeight() * intervalHoles;
			listRocks.add(hole);
		}
	}

	public void onDraw(Canvas canvas, Paint paint) {
		if (listRocks != null && !listRocks.isEmpty()) {
			for (int i = 0; i < listRocks.size(); i++) {
				if (listRocks.get(i) != null)
					listRocks.get(i).onDraw(canvas, paint);
			}
		}
	}

	public void update() {
		if (listRocks != null && !listRocks.isEmpty()) {
			for (int i = 0; i < listRocks.size(); i++) {
				listRocks.get(i).move(0, -speed);
				if (listRocks.get(i).getY() < -listRocks.get(i).getHeight()) {
					int last = getLastRockInMap();
					float lastY = listRocks.get(last).getY()
							+ listRocks.get(i).getHeight() * intervalHoles;
					setNewXPosition(last);
					listRocks.get(i).setX(posX);
					listRocks.get(i).setY(lastY);
				}
			}
		}
	}

	private int getLastRockInMap() {
		int search = -1;
		if (listRocks != null && !listRocks.isEmpty()) {
			float max = 0;
			for (int i = 0; i < listRocks.size(); i++) {
				if (listRocks.get(i).getY() > max) {
					max = listRocks.get(i).getY();
					search = i;
				}
			}
		}
		return search;
	}

	private void setNewXPosition(int n) {
		if (listRocks.get(n).getX() + listRocks.get(n).getWidth() / 2 < Assets.DEFAULT_WIDTH / 2) {
			posX = Util.getRandomNumber(Assets.DEFAULT_WIDTH / 2
					+ listRocks.get(n).getWidth(), Assets.DEFAULT_WIDTH
					- listRocks.get(n).getWidth());
		} else {
			posX = Util.getRandomNumber(0, Assets.DEFAULT_WIDTH / 2);
		}
	}

	public void clear() {
		if (listRocks != null)
			listRocks.clear();
		listRocks = null;
	}

	public boolean collidesWithMario(Player mario) {
		if (listRocks != null && !listRocks.isEmpty()) {
			for (int i = 0; i < listRocks.size(); i++) {
				if (listRocks.get(i).isCollide(mario)) {
					return true;
				}
			}
		}
		return false;
	}

	public void incrementSpeed() {
		speed++;
	}

	public List<Rock> getListRocks() {
		return listRocks;
	}

}
