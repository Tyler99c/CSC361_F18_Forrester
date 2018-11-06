package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.mygdx.dangerdungeon.MyGdxGame;

/**
 * Launches the game
 * @author tf0199
 *
 */
public class DesktopLauncher 
{
	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = true;
	
	public static void main (String[] arg) 
	{
		
		if (rebuildAtlas)
		{
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = drawDebugOutline;
			TexturePacker.process(settings, "assets-raw/images", "../core/assets/images", "dangerdungeon.pack");
		}
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.title = "Dangerdungeon";
		//config.width = 800;
		//config.height = 480;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
