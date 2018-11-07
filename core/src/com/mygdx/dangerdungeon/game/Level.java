package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.dangerdungeon.objects.AbstractGameObject;
import com.mygdx.dangerdungeon.objects.Floor;
import com.mygdx.dangerdungeon.objects.Knight;
import com.mygdx.dangerdungeon.objects.WallUp;
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
		EMPTY(0,0,0), FLOOR(255,174,201), KNIGHT(255,255,255), WALL_UP(255,204,201);
		
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
	public Knight knight;
	public Array<WallUp> wall_up;
	
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
		wall_up = new Array<WallUp>();
		
		//player character
		knight = null;
		
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
				else if (BLOCK_TYPE.KNIGHT.sameColor(currentPixel))
				{
					obj = new Floor();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					floor.add((Floor)obj);
					System.out.println("Founds a floor pixel");
					obj = new Knight();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					knight = (Knight)obj;
				}
				else if (BLOCK_TYPE.WALL_UP.sameColor(currentPixel))
				{
					obj = new WallUp();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_up.add((WallUp)obj);
				}
				else
				{
					int r = 0xff & (currentPixel >>> 24); //red color channel
					int g = 0xff & (currentPixel >>> 16); //green color channel
					int b = 0xff & (currentPixel >>> 8); //blue color channel
					int a = 0xff & currentPixel; //Alpha Channel
					Gdx.app.error(TAG, "Unkonw objec at x<" + pixelX + "> y<" + pixelY + ">: r<" + r+ "> g<" + g + "> b<" + b + "> a<" + a + ">");
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
		//Draw Player Character
		knight.render(batch);
		//Draws the walls
		for(WallUp wall_up : wall_up)
			wall_up.render(batch);
	}
}
