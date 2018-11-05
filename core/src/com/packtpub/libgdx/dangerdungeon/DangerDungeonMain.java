package com.packtpub.libgdx.dangerdungeon;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogix.gdx.graphics.GL20;

public class DangerDungeonMain extends Game{

	@Override
	public void create() {
		//Set Libgdx lgo level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Load assets
		Assets.instance.init(new AssetManager());
		//Loads perefences for audio settings and astarting playing
		//Starts game at menu screen
		setScreen(new GameScreen(this));
	}
	//@Override public void render() {}
	//@Override public void resize(int width, int height) {}
	//@Override public void pause() {}
	//@Override public void resume() {}
	//@Override public void dispose() {}
	

}
