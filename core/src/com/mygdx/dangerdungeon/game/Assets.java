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

	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
	public class AssetLevelDecoration
	{
		public final AtlasRegion floor;
		public final AtlasRegion wall;
		
		public AssetLevelDecoration (TextureAtlas atlas)
		{
			wall = atlas.findRegion("Wall");
			
		}
	}
	
	//singleton: prevent instantiation form other classes
	private Assets() {}
	
	public AssetPlayer player;
	
	public void init (AssetManager assetManager) 
	{
		this.assetManager = assetManager;
		//set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS,TextureAtlas.class);
		//start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.degbu(TAG, "# of assets loaded: " + assetManager.finishLoading());
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);
	}
	
	TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
	
	//Enable texture filtering for pixel smoothing
	for (Texture t : atlas.getTextures())
	{
		t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	//create game resource objects
	player = new AssetBunny(atlas);
	
	@Override
	public void dispose()
	{
		assetManager.dispose();
	}
	
	public void error(String filename, Class type, Throwable throwable)
	{
		Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception)throwable);
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}
}
