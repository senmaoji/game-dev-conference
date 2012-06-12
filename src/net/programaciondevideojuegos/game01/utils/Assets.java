package net.programaciondevideojuegos.game01.utils;


public class Assets {

	private static final String AUDIO_PATH = "audio/";
	private static final String GFX_PATH = "gfx/";

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

	public static final int SCORE_FOR_NEXT_LEVEL = 250;

	public static final String URL_SCORE = "http://carlospinan.appspot.com/";
	public static final String PARAM_PLAYER = "player";
	public static final String PARAM_SCORE = "score";

	/**
	 * Asset for the game
	 */
	public static final String asset_background = GFX_PATH + "background.png";
	public static final String asset_banana = GFX_PATH + "banana.png";
	public static final String asset_hole = GFX_PATH + "hole.png";
	public static final String asset_kart = GFX_PATH + "mario_kart.png";
	public static final String asset_lights[] = { GFX_PATH + "light_0.png",
			GFX_PATH + "light_1.png", GFX_PATH + "light_2.png",
			GFX_PATH + "light_3.png" };

	/**
	 * 
	 */

	public static final String sound_background = AUDIO_PATH + "smwwd2.mid";
	public static final String sound_gameover = AUDIO_PATH + "game_over.mp3";
}
