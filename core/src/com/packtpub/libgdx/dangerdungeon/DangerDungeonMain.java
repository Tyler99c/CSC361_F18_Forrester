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
		//Start game at menu screen
		setScreen(new MenuScreen(this));
	}
//	/**
//	 * Draws objects
//	 */
//	@Override
//	public void render() 
//	{
//		//Do not game world when paused.
//		if (!paused ) {
//			//Update game world by the time that has passed
//			//since last rendererd frame.
//			worldController.update(Gdx.graphics.getDeltaTime());
//		}
//		//Sets the clear screen cloror to: Cornflower Blue
//		Gdx.gl.glClearColor(0x33/255.0f, 0x33/255.0f, 0x33/255.0f, 0xff/255.0f);
//		//Clears the screen
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		
//		//Render game world to screen
//		worldRenderer.render();
//	}
//
//	/**
//	 * Pauses the game
//	 */
//	@Override
//	public void pause() 
//	{
//		paused = true;
//	}
//
//	/**
//	 * resumes the game
//	 */
//	@Override
//	public void resume() 
//	{
//		paused = false;
//	}
//
//	/**
//	 * Disposes unused memory
//	 */
//	@Override
//	public void dispose() 
//	{
//		worldRenderer.dispose();
//		Assets.instance.dispose();
//		
//	}
}
