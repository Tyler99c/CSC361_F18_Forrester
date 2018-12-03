package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.dangerdungeon.objects.Spikes;
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
	public AssetWallDown wall_down;
	public AssetWallRight wall_right;
	public AssetWallLeft wall_left;
	public AssetWallTopLeft wall_topleft;
	public AssetWallBottomLeft wall_bottomleft;
	public AssetWallBottomRight wall_bottomright;
	public AssetWallTopRight wall_topright;
	public AssetFonts fonts;
	
	public AssetLevelDecoration levelDecoration;
	
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
		wall_down = new AssetWallDown(atlas);
		wall_left = new AssetWallLeft(atlas);
		wall_right = new AssetWallRight(atlas);
		wall_topleft = new AssetWallTopLeft(atlas);
		wall_bottomleft = new AssetWallBottomLeft(atlas);
		wall_topright = new AssetWallTopRight(atlas);
		wall_bottomright = new AssetWallBottomRight(atlas);
		fonts = new AssetFonts();
		levelDecoration = new AssetLevelDecoration(atlas);
	}
	
	
	public class AssetFonts
	{
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;
		
		public AssetFonts()
		{
			//create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"),true);
			defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"),true);
			defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"),true);
			//set font sizes
			defaultSmall.getData().setScale(0.75f);
			defaultNormal.getData().setScale(1.0f);
			defaultBig.getData().setScale(2.0f);
			//enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear,  TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear,TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
		}
		
	}
	/**
	 * Disposes the unused memory
	 */
	@Override
	public void dispose() 
	{
		assetManager.dispose();
		fonts.defaultSmall.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultBig.dispose();
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
		public final AtlasRegion knightFront;
		public final Animation<AtlasRegion> animFront;
		public final Animation<AtlasRegion> animBack;
		public final Animation<AtlasRegion> animSide;
		public final Animation<AtlasRegion> animOtherSide;
		public final AtlasRegion knightBack;
		public final AtlasRegion knightSide;
		public final AtlasRegion knightOtherSide;
		
	
		
		public AssetKnight(TextureAtlas atlas)
		{
			knightFront = atlas.findRegion("Knightoofront_01");
			knightBack = atlas.findRegion("Knightooback_01");
			knightSide = atlas.findRegion("KnightSide_01");
			knightOtherSide = atlas.findRegion("KnightOtherSide_01");
			
			
			Array<AtlasRegion> regions = null;
			//AtlasRegion region = null;
			
			//Animation: Front
			regions = atlas.findRegions("Knightoofront");
			animFront = new Animation<AtlasRegion>(1.0f/6.0f, regions, Animation.PlayMode.LOOP);
			
			//Animation: Back
			regions = atlas.findRegions("Knightooback");
			animBack = new Animation<AtlasRegion>(1.0f/6.0f, regions, Animation.PlayMode.LOOP);
			
			//Animation: Side
			regions = atlas.findRegions("Knightooside");
			animSide = new Animation<AtlasRegion>(1.0f/ 6.0f, regions, Animation.PlayMode.LOOP);
			
			//Animation: OtherSide
			regions = atlas.findRegions("Knightoosideother");
			animOtherSide = new Animation<AtlasRegion>(1.0f/ 6.0f, regions, Animation.PlayMode.LOOP);
			
			
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
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallUp
	{
		public final AtlasRegion wall_up;
		
		public AssetWallUp(TextureAtlas atlas)
		{
			wall_up = atlas.findRegion("wall_up");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallDown
	{
		public final AtlasRegion wall_down;
		
		public AssetWallDown(TextureAtlas atlas)
		{
			wall_down = atlas.findRegion("wall_down");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallRight
	{
		public final AtlasRegion wall_right;
		
		public AssetWallRight(TextureAtlas atlas)
		{
			wall_right = atlas.findRegion("wall_right");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallLeft
	{
		public final AtlasRegion wall_left;
		
		public AssetWallLeft(TextureAtlas atlas)
		{
			wall_left = atlas.findRegion("wall_left");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallTopLeft
	{
		public final AtlasRegion wall_topleft;
		
		public AssetWallTopLeft(TextureAtlas atlas)
		{
			wall_topleft = atlas.findRegion("wall_topleft");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallBottomLeft
	{
		public final AtlasRegion wall_bottomleft;
		
		public AssetWallBottomLeft(TextureAtlas atlas)
		{
			wall_bottomleft = atlas.findRegion("wall_bottomleft");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallTopRight
	{
		public final AtlasRegion wall_topright;
		
		public AssetWallTopRight(TextureAtlas atlas)
		{
			wall_topright = atlas.findRegion("wall_topright");
		}
	}
	
	/**
	 * Handles the one of the wall asset
	 * @author Tyler Forrester
	 *
	 */
	public class AssetWallBottomRight
	{
		public final AtlasRegion wall_bottomright;
		
		public AssetWallBottomRight(TextureAtlas atlas)
		{
			wall_bottomright = atlas.findRegion("wall_bottomright");
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
		public final AtlasRegion spikes;
		
		public AssetLevelDecoration (TextureAtlas atlas)
		{
			floor = atlas.findRegion("floor");
			spikes = atlas.findRegion("Spikes");
		}
	}
	

}
