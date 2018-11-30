package com.packtpub.libgdx.dangerdungeon.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.dangerdungeon.game.WorldController;
import com.mygdx.dangerdungeon.game.WorldRenderer;
import com.packtpub.libgdx.dangerdungeon.DangerDungeonMain;


/**
 * This class will handle the game
 * @author Tyler Forrester
 *
 */
public class GameScreen extends AbstractGameScreen
{

	private static final String TAG = DangerDungeonMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;

	public GameScreen(Game game) 
	{
		super(game);
	}

	/**
	 * Draws game
	 */
	@Override
	public void render(float deltaTime) 
	{
		//Do not update game world when paused.
		if (!paused)
		{
			//Update game world by the time that has passed
			//since last rendered frame.
			worldController.update(deltaTime);
		}
		//Sets the clear screen cloror to: Cornflower Blue
		Gdx.gl.glClearColor(0x33/255.0f, 0x33/255.0f, 0x33/255.0f, 0xff/255.0f);
		//Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Render game world to screen
		worldRenderer.render();
	}

	/**
	 * Resizes the window
	 */
	@Override
	public void resize(int width, int height) 
	{
		worldRenderer.resize(width, height);	
	}

	/**
	 * Shows things on the screen
	 */
	@Override
	public void show() 
	{
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * Hides things on the screen
	 */
	@Override
	public void hide() 
	{
		worldRenderer.dispose();
		Gdx.input.setCatchBackKey(false);
		
	}

	/**
	 * Pauses the game
	 *
	 */
	@Override
	public void pause() 
	{
		paused = true;
		
	}
	
	/**
	 * Resumes the game
	 */
	@Override
	public void resume()
	{
		super.resume();
		paused = false;
	}


}
