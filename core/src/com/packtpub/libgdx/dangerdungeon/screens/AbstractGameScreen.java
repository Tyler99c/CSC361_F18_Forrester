package com.packtpub.libgdx.dangerdungeon.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.dangerdungeon.game.Assets;
import com.mygdx.dangerdungeon.game.WorldController;
import com.mygdx.dangerdungeon.game.WorldRenderer;
import com.packtpub.libgdx.dangerdungeon.DangerDungeonMain;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Provides a framework for Abstract Game screen.
 * @author Tyler Forrester
 *
 */
public class AbstractGameScreen implements ApplicationListener
{
	private static final String TAG = DangerDungeonMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
	/**
	 * Provides framework for the screens
	 */
	public void init() { }
	
	/**
	 * Updates the screen
	 * @param deltaTime
	 */
	public void update(float deltaTime) { }
	
	/**
	 * Creates the screen
	 */
	@Override public void create() 
	{
		//Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		//Game world is active on start
		paused = false;
	}

	/**
	 * Resizes the screen
	 */
	@Override
	public void resize(int width, int height) 
	{
		worldRenderer.resize(width, height);
	}

	/**
	 * Draws images on the screen
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
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
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
	 * Resumes teh game
	 */
	@Override
	public void resume() 
	{
		paused = false;
	}

	/**
	 * Disposes the memory
	 */
	@Override
	public void dispose() 
	{
		worldRenderer.dispose();
		
	}
}
