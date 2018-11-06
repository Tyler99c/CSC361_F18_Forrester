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
	
	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
	//singleton: prevent instantiation form other classes
	private Assets() 
	{
	}
	
	
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
				t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		//create game resource objects
		knight = new AssetKnight(atlas);
		chest = new AssetChest(atlas);
		floor = new AssetFloor(atlas);
	}
	

	@Override
	public void dispose() 
	{
		
	}
	
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) 
	{
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
	}
	
	public class AssetKnight
	{
		public final AtlasRegion head;
		
		public AssetKnight(TextureAtlas atlas)
		{
			head = atlas.findRegion("knight");
		}
	}
	
	public class AssetFloor
	{
		public final AtlasRegion floor;
		
		public AssetFloor(TextureAtlas atlas)
		{
			floor = atlas.findRegion("floor");
		}
		
	}
	
	public class AssetChest
	{
		public final AtlasRegion chest;
		
		public AssetChest(TextureAtlas atlas)
		{
			chest = atlas.findRegion("chest");
		}
	}
	
	public class AssetLevelDecoration
	{
		public final AtlasRegion floor;
		
		
		public AssetLevelDecoration (TextureAtlas atlas)
		{
			floor = atlas.findRegion("floor");
		}
	}
	

}
