package com.packtpub.libgdx.dangerdungeon;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class DangerDungeonMain extends Game{

	@Override
	public void create() {
		//Set Libgdx log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Load assets
		assets.instance.init(new AssetManger());
		//Load prefrences for audio settings
		GamePrefrences.instance.load();
		AudioManager.instance.play(Assets.instance.music.song01);
		// Start game at game screen... for now
		setScreen(new GameScreen(this));
	}

}
