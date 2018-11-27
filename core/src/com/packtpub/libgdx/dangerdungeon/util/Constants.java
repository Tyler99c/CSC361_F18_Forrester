package com.packtpub.libgdx.dangerdungeon.util;

/**
 * Constants class that effects the game's viewport
 * @author Tyler Forrester
 *
 */
public class Constants 
{

	//Visible game world is 5 meters wide
	public static final float VIEWPORT_GUI_WIDTH = 800.0f;
	
	//Visible game world is 5 meters tall
	public static final float VIEWPORT_GUI_HEIGHT = 480.0f;
	//Location of description file for texture atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "images/dangerdungeon.pack.atlas";
	//Location of image file for level 01
	public static final String LEVEL_01 = "levels/level-01.png";
	//Visible game world is 5 meters wide
	public static final float VIEWPORT_WIDTH = 25.0f;
	
	//Visible game world is 5 meters tall
	public static final float VIEWPORT_HEIGHT = 25.0f;
	
	public static final float OFFSET = 1f;
	
	// Information for displaying ui elements
	public static final String TEXTURE_ATLAS_UI = "images/dangerdungeon-ui.pack.atlas";
	public static final String TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas";
	public static final String SKIN_LIBGDX_UI = "images/uiskin.json";
	public static final String SKIN_DANGERDUNGEON_UI = "images/dangerdungeon-ui.json";
	
	public static final String PREFERENCES = "dangerdungeon.prefs";
}