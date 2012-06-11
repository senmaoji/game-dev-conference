package net.programaciondevideojuegos.game01.utils;

import net.programaciondevideojuegos.game01.R;

public class Assets {

	private static final String AUDIO_PATH = "audio/";

	public static final int DEFAULT_WIDTH = 320;
	public static final int DEFAULT_HEIGHT = 480;
	public static float SCALEX;
	public static float SCALEY;
	public static int SCORE;

	public static int BANANA_SPEED;
	public static int BANANA_INTERVAL;

	public static int HOLE_SPEED;
	public static int HOLE_INTERVAL;

	public static int MAX_BANANAS = 15;
	public static int MAX_HOLES = 10;

	public static String LEVEL;

	public static final int SCORE_FOR_NEXT_LEVEL = 500;

	public static final String URL_SCORE = "http://carlospinan.appspot.com/";
	public static final String PARAM_PLAYER = "player";
	public static final String PARAM_SCORE = "score";

	/**
	 * Asset for the game
	 */
	public static final int asset_background = R.drawable.background;
	public static final int asset_banana = R.drawable.banana;
	public static final int asset_hole = R.drawable.hole;
	public static final int asset_kart = R.drawable.mario_kart;
	public static final int asset_lights[] = { R.drawable.light_0,
			R.drawable.light_1, R.drawable.light_2, R.drawable.light_3 };

	/**
	 * 
	 */

	public static final String sound_background = AUDIO_PATH + "smwwd2.mid";
	public static final String sound_gameover = AUDIO_PATH + "game_over.mp3";
}
