package com.packtpub.libgdx.dangerdungeon;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.dangerdungeon.game.Assets;
import com.mygdx.dangerdungeon.game.WorldController;
import com.mygdx.dangerdungeon.game.WorldRenderer;

/**
 * A main class that starts and handles the game
 * @author Tyler Forrester
 *
 */
public class DangerDungeonMain extends Game
{
	private static final String TAG = DangerDungeonMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
	/**
	 * Initiates the DangerDungeon class
	 */
	public void init() { }
	
	/**
	 * Updates the main class
	 * @param deltaTime
	 */
	public void update(float deltaTime) { }
	
	/**
	 * creates the level
	 */
	@Override public void create() 
	{
		//Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Load assets
		Assets.instance.init(new AssetManager());
		//Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		//Game world is active on start
		paused = false;
		//Load assets
		Assets.instance.init(new AssetManager());
	}

	/**
	 * Resizes the game when the screen is messed with
	 */
	@Override
	public void resize(int width, int height) 
	{
		worldRenderer.resize(width, height);
	}

	/**
	 * Draws objects
	 */
	@Override
	public void render() 
	{
		//Do not game world when paused.
		if (!paused ) {
			//Update game world by the time that has passed
			//since last rendererd frame.
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		//Sets the clear screen cloror to: Cornflower Blue
		Gdx.gl.glClearColor(0x33/255.0f, 0x33/255.0f, 0x33/255.0f, 0xff/255.0f);
		//Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Render game world to screen
		worldRenderer.render();
	}

	/**
	 * Pauses the game
	 */
	@Override
	public void pause() 
	{
		paused = true;
	}

	/**
	 * resumes the game
	 */
	@Override
	public void resume() 
	{
		paused = false;
	}

	/**
	 * Disposes unused memory
	 */
	@Override
	public void dispose() 
	{
		worldRenderer.dispose();
		Assets.instance.dispose();
		
	}
}
