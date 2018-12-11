package com.packtpub.libgdx.dangerdungeon;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.dangerdungeon.game.Assets;
import com.mygdx.dangerdungeon.game.WorldController;
import com.mygdx.dangerdungeon.game.WorldRenderer;
import com.packtpub.libgdx.dangerdungeon.screens.GameScreen;
import com.packtpub.libgdx.dangerdungeon.screens.MenuScreen;
import com.packtpub.libgdx.dangerdungeon.util.AudioManager;
import com.packtpub.libgdx.dangerdungeon.util.GamePreferences;

/**
 * A main class that starts and handles the game
 * @author Tyler Forrester
 *
 */
public class DangerDungeonMain extends Game
{
	/**
	 * creates the level
	 */
	@Override public void create() 
	{
		//Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Load assets
		Assets.instance.init(new AssetManager());
		//Load preferences for audio settings and start playing music
		GamePreferences.instance.load();
		//Start game at menu screen
		setScreen(new MenuScreen(this));
	}

}
