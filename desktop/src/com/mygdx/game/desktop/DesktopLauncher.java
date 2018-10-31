package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.mygdx.game.MyGdxGame;

/**
 * Launches the game
 * @author tf0199
 *
 */
public class DesktopLauncher {
	private static boolean rebuildAtlas = false;
	private static boolean drawDebugOutline = false;
	
	public static void main (String[] arg) {
		
		if (rebuildAtlas)
		{
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = drawDebugOutline;
			TexturePacker.process(settings, "assets-raw/images", "../core/assets/images", "dangerdungeon.pack");
			//TexturePacker.process(settings, "assets-raw/images-ui", "../core/assets/images", "canyonbunny-ui.pack");
		}
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
	}
}