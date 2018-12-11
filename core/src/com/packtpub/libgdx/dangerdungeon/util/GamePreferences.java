package com.packtpub.libgdx.dangerdungeon.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
*
* Place to store the game's pref's as avious by the title
* @Author Tyler Forrester
 */
public class GamePreferences
{
	public static final String TAG = GamePreferences.class.getName();

	public static final GamePreferences instance = new GamePreferences();

	public boolean sound, music;
	public float volSound, volMusic;
	public int charSkin;
	public boolean showFpsCounter;
	//ITs time to make THE WORST ARRAY EVVVVVVVVVAAAAAAAAARRRRRRR
	public int[] highscore = new int[10];

	private Preferences prefs;

	// singleton: prevents instantiation from other classes
	private GamePreferences()
	{
		prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
	}

	/**
	 * Loads game settings as preserved by user
	 */
	public void load()
	{
		sound = prefs.getBoolean("sound", true);
		music = prefs.getBoolean("music", true);

		volMusic = com.badlogic.gdx.math.MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
		volSound = com.badlogic.gdx.math.MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);

		charSkin = com.badlogic.gdx.math.MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);

		showFpsCounter = prefs.getBoolean("showFpsCounter", false);
		
		for(int i = 0; i < 10; i++)
		{
			String overload = "highscore" + i;
			highscore[i] = prefs.getInteger(overload);
		}
	}

	/**
	 * Save settings of the game as set by user
	 */
	public void save()
	{
		prefs.putBoolean("sound", sound);
		prefs.putBoolean("music", music);
		prefs.putFloat("volSound", volSound);
		prefs.putFloat("volMusic", volMusic);
		prefs.putInteger("charSkin", charSkin);
		prefs.putBoolean("showFpsCounter", showFpsCounter);
		for(int i = 0; i < 10; i++)
		{
			String overload = "highscore" + i;
			prefs.putInteger(overload, highscore[i]);
		}
		prefs.flush();
	}
}
