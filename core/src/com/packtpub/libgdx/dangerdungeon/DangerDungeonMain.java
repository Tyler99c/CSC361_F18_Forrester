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
		//Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
	}
	@Override public void render() {}
	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}
	

}
