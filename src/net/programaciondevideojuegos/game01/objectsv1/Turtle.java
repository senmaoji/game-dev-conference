package net.programaciondevideojuegos.game01.objectsv1;

import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.SoundManager;
import net.programaciondevideojuegos.game01.utils.Sprite2D;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.graphics.Canvas;

public class Turtle {

	private final int TURTLE_APPEAR = 3000;
	private long currentTime = -1;
	private Sprite2D greenTurtle;
	private Sprite2D redTurtle;
	private boolean isTurtleInAction = false;
	private byte turtleInAction = 1; // 0 = greenTurtle; 1 = Red turtle is
										// Cyclic
	private int speedGreenY = -5, speedRedY = -2;
	private int speedX = 7;
	private Context context = null;

	public Turtle(Context context) {
		this.context = context;
		greenTurtle = new Sprite2D(Util.decodeBitmap(context.getResources(),
				Assets.asset_green_turtle), 0, 0);

		redTurtle = new Sprite2D(Util.decodeBitmap(context.getResources(),
				Assets.asset_red_turtle), 0, 0);
		currentTime = System.currentTimeMillis();
	}

	public void onDraw(Canvas canvas) {
		if (isTurtleInAction) {
			if (turtleInAction == 0) {
				greenTurtle.onDraw(canvas, null);
			} else {
				redTurtle.onDraw(canvas, null);
			}
		}
	}

	public void update() {
		if (!isTurtleInAction && currentTime > -1
				&& System.currentTimeMillis() - currentTime > TURTLE_APPEAR) {
			if (turtleInAction == 1) {
				turtleInAction = 0;
				greenTurtle.setPosition(
						Util.getRandomNumber(0, Assets.DEFAULT_WIDTH
								- greenTurtle.getWidth()),
						Assets.DEFAULT_HEIGHT);
			} else {
				turtleInAction = 1;
				redTurtle.setPosition(
						Util.getRandomNumber(0, Assets.DEFAULT_WIDTH
								- redTurtle.getWidth()), Assets.DEFAULT_HEIGHT);
			}
			currentTime = -1;
			isTurtleInAction = true;
		} else if (isTurtleInAction && currentTime == -1) {
			if (turtleInAction == 0) {
				greenTurtle.move(0, speedGreenY);
				if (greenTurtle.getY() < -greenTurtle.getHeight()) {
					isTurtleInAction = false;
					currentTime = System.currentTimeMillis();
				}
			} else {
				redTurtle.move(speedX, speedRedY);
				if (redTurtle.getY() < 0) {
					isTurtleInAction = false;
					currentTime = System.currentTimeMillis();
				} else {
					if (redTurtle.getX() < 0) {
						SoundManager.playSFX(context, Assets.sound_hit);
						redTurtle.setX(0);
						speedX = 10;
					} else if (redTurtle.getX() > Assets.DEFAULT_WIDTH
							- redTurtle.getWidth()) {
						SoundManager.playSFX(context, Assets.sound_hit);
						redTurtle.setX(Assets.DEFAULT_WIDTH
								- redTurtle.getWidth());
						speedX = -10;
					}
				}
			}
		}
	}

	public void clear() {
		currentTime = -1;
		greenTurtle = null;
		redTurtle = null;
	}

	public boolean collidesWithMario(Player mario) {
		if (isTurtleInAction) {
			if (turtleInAction == 0 && greenTurtle.isCollide(mario))
				return true;
			if (turtleInAction == 1 && redTurtle.isCollide(mario))
				return true;
		}
		return false;
	}

	public void incrementSpeed() {
		speedGreenY--;
		speedRedY--;
		speedX++;
	}

}
