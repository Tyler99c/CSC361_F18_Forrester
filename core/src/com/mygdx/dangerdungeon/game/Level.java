package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.dangerdungeon.objects.AbstractGameObject;
import com.mygdx.dangerdungeon.objects.Floor;
import com.packtpub.libgdx.dangerdungeon.util.*;

/**
 * This class reads the level data and draws the level
 * @author Tyler Forrester
 *
 */
public class Level {
	public static final String TAG = Level.class.getName();
	
	/**
	 * Gives data to the level image so we draw the level in the game 
	 * based on the image data.
	 *
	 */
	public enum BLOCK_TYPE
	{
		EMPTY(0,0,0), FLOOR(255,174,201);
		
		private int color;
		
		private BLOCK_TYPE(int r, int g, int b)
		{
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		
		public boolean sameColor(int color)
		{
			return this.color == color;
		}
		
		public int getColor()
		{
			return color;
		}
	}
	
	//objects
	public Array<Floor> floor;
	
	/**
	 * Creates the level instance
	 * @param filename
	 */
	public Level(String filename)
	{
		init(filename);
	}
	
	/**
	 * Initiatez the  level
	 * @param filename
	 */
	private void init(String filename) {
		//objects
		floor = new Array<Floor>();
		
		//load image file that represents the elvel data
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		//scane pixels form top-left to botom-right
		int lastPixel = -1;
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++)
		{
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++)
			{
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				//height groaws from bottom to top
				float baseHeight = pixmap.getHeight() - pixelY;
				//get color of current pixel as 32bit RGBA calue
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				//find matcing color value to identify block type at (x,y) point and create the corresponding game object if there is a match
				
				//empy space
				if (BLOCK_TYPE.EMPTY.sameColor(currentPixel))
				{
					//do nothing
				}
				//rock
				else if (BLOCK_TYPE.FLOOR.sameColor(currentPixel))
				{
					obj = new Floor();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					floor.add((Floor)obj);
					System.out.println("Founds a floor pixel");
				}
			}
		}
		
		//free memoery
		pixmap.dispose();
		Gdx.app.debug(TAG,  "level '" + filename + "' loaded");
	}
	
	/**
	 * Draws the level after it's loaded in
	 * @param batch
	 */
	public void render(SpriteBatch batch) 
	{ 
		//Draws the floor
		for(Floor floor : floor)
			floor.render(batch);
	}
}
