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
public abstract class AbstractGameScreen implements Screen
{
	protected Game game;
	
	public AbstractGameScreen (Game game)
	{
		this.game = game;
	}
	
	public abstract void render(float deltaTime);
	public abstract void resize(int width, int height);
	public abstract void show();
	public abstract void hide();
	public abstract void pause();

	/**
	 * Resumes the game
	 */
	@Override
	public void resume() 
	{
		Assets.instance.init(new AssetManager());
	}

	/**
	 * Brings all objects to a halt
	 */
	@Override
	public void dispose() 
	{
		Assets.instance.dispose();
	}
}
