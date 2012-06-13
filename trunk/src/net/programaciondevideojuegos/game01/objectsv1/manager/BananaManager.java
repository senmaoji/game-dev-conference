package net.programaciondevideojuegos.game01.objectsv1.manager;

import java.util.ArrayList;
import java.util.List;

import net.programaciondevideojuegos.game01.objectsv1.Banana;
import net.programaciondevideojuegos.game01.objectsv1.Player;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BananaManager {

	private List<Banana> listBananas = null;
	private float posY = 300, posX = 0;
	private int speed, intervalBananas;

	public BananaManager(Context context) {
		listBananas = new ArrayList<Banana>();
		speed = Assets.BANANA_SPEED;
		intervalBananas = Assets.BANANA_INTERVAL;
		Bitmap bmp = Util.decodeBitmap(context.getResources(),
				Assets.asset_banana);

		posX = Util.getRandomNumber(0, Assets.DEFAULT_WIDTH - bmp.getWidth());
		for (int i = 0; i < Assets.MAX_BANANAS; i++) {
			if (i > 0)
				setNewXPosition(i - 1);
			Banana banana = new Banana(bmp, posX, posY);
			posY += bmp.getHeight() * intervalBananas;
			listBananas.add(banana);
		}
	}

	public void onDraw(Canvas canvas, Paint paint) {
		if (listBananas != null && !listBananas.isEmpty()) {
			for (int i = 0; i < listBananas.size(); i++) {
				listBananas.get(i).onDraw(canvas, paint);
			}
		}
	}

	public void update() {
		if (listBananas != null && !listBananas.isEmpty()) {
			for (int i = 0; i < listBananas.size(); i++) {
				listBananas.get(i).move(0, -speed);
				if (listBananas.get(i).getY() < -listBananas.get(i).getHeight()) {
					int last = getLastBananaInMap();
					float lastY = listBananas.get(last).getY()
							+ listBananas.get(i).getHeight() * intervalBananas;
					setNewXPosition(last);
					listBananas.get(i).setX(posX);
					listBananas.get(i).setY(lastY);
				}
			}
		}
	}

	private int getLastBananaInMap() {
		int search = -1;
		if (listBananas != null && !listBananas.isEmpty()) {
			float max = 0;
			for (int i = 0; i < listBananas.size(); i++) {
				if (listBananas.get(i).getY() > max) {
					max = listBananas.get(i).getY();
					search = i;
				}
			}
		}
		return search;
	}

	private void setNewXPosition(int n) {
		if (listBananas.get(n).getX() + listBananas.get(n).getWidth() / 2 < Assets.DEFAULT_WIDTH / 2) {
			posX = Util.getRandomNumber(Assets.DEFAULT_WIDTH / 2
					+ listBananas.get(n).getWidth(), Assets.DEFAULT_WIDTH
					- listBananas.get(n).getWidth());
		} else {
			posX = Util.getRandomNumber(0, Assets.DEFAULT_WIDTH / 2);
		}
	}

	public void clear() {
		if (listBananas != null)
			listBananas.clear();
		listBananas = null;
	}

	public boolean collidesWithMario(Player mario) {
		if (listBananas != null && !listBananas.isEmpty()) {
			for (int i = 0; i < listBananas.size(); i++) {
				if (listBananas.get(i).isCollide(mario)) {
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
