package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.packtpub.libgdx.dangerdungeon.util.Constants;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

/**
 * Handles the textures for the assets
 * @author Tyler Forrester
 *
 */
public class Assets implements Disposable, AssetErrorListener
{

	public AssetKnight knight;
	public AssetChest chest;
	public AssetFloor floor;
	public AssetWallUp wall_up;
	
	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
	//singleton: prevent instantiation form other classes
	private Assets() 
	{
	}
	
	/**
	 * Initiates the assets class
	 * @param assetManager
	 */
	public void init (AssetManager assetManager) 
	{
		this.assetManager = assetManager;
		//set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		//start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames());
		for (String a : assetManager.getAssetNames())
		{
			Gdx.app.debug(TAG, "asset: " + a);
		}
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		
		//enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures())
		{
				t.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		}
		
		//create game resource objects
		knight = new AssetKnight(atlas);
		chest = new AssetChest(atlas);
		floor = new AssetFloor(atlas);
		wall_up = new AssetWallUp(atlas);
	}
	

	/**
	 * Disposes the unused memory
	 */
	@Override
	public void dispose() 
	{
		assetManager.dispose();
	}
	
	/**
	 * Handles when an error with assets show up
	 */
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) 
	{
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
	}
	
	/**
	 * Handles the knight player
	 * @author Tyler Forrester
	 *
	 */
	public class AssetKnight
	{
		public final AtlasRegion knight;
		
		public AssetKnight(TextureAtlas atlas)
		{
			knight = atlas.findRegion("knight");
		}
	}
	
	/**
	 * Handles the floor asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetFloor
	{
		public final AtlasRegion floor;
		
		public AssetFloor(TextureAtlas atlas)
		{
			floor = atlas.findRegion("floor");
		}
		
	}
	
	/**
	 * Handles the chest asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetChest
	{
		public final AtlasRegion chest;
		
		public AssetChest(TextureAtlas atlas)
		{
			chest = atlas.findRegion("chest");
		}
	}
	
	public class AssetWallUp
	{
		public final AtlasRegion wall_up;
		
		public AssetWallUp(TextureAtlas atlas)
		{
			wall_up = atlas.findRegion("wall_up");
		}
	}
	
	/**
	 * Handles the Level decoration, don't know if I'll need this, but oh well
	 * @author Tyler Forrester
	 *
	 */
	public class AssetLevelDecoration
	{
		public final AtlasRegion floor;
		
		
		public AssetLevelDecoration (TextureAtlas atlas)
		{
			floor = atlas.findRegion("floor");
		}
	}
	

}
