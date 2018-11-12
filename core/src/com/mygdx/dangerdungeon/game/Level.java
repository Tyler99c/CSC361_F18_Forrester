package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.dangerdungeon.objects.AbstractGameObject;
import com.mygdx.dangerdungeon.objects.Floor;
import com.mygdx.dangerdungeon.objects.Knight;
import com.mygdx.dangerdungeon.objects.WallBottomLeft;
import com.mygdx.dangerdungeon.objects.WallBottomRight;
import com.mygdx.dangerdungeon.objects.WallDown;
import com.mygdx.dangerdungeon.objects.WallLeft;
import com.mygdx.dangerdungeon.objects.WallRight;
import com.mygdx.dangerdungeon.objects.WallTopLeft;
import com.mygdx.dangerdungeon.objects.WallTopRight;
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
		EMPTY(0,0,0), FLOOR(255,174,201), KNIGHT(255,255,255), WALL_UP(255,204,201), WALL_DOWN(255,254,201),WALL_RIGHT(255,50,201),WALL_LEFT(50,50,201),WALL_TOPRIGHT(128,255,0),WALL_TOPLEFT(181,230,29),WALL_BOTTOMLEFT(0,128,64),WALL_BOTTOMRIGHT(34,177,76);
		
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
	public Array<WallDown> wall_down;
	public Array<WallRight> wall_right;
	public Array<WallLeft> wall_left;
	public Array<WallBottomRight> wall_bottomright;
	public Array<WallTopRight> wall_topright;
	public Array<WallBottomLeft> wall_bottomleft;
	public Array<WallTopLeft> wall_topleft;

	
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
		wall_down = new Array<WallDown>();
		wall_right = new Array<WallRight>();
		wall_left = new Array<WallLeft>();
		wall_bottomleft = new Array<WallBottomLeft>();
		wall_topleft = new Array<WallTopLeft>();
		wall_bottomright = new Array<WallBottomRight>();
		wall_topright = new Array<WallTopRight>();
		
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
					offsetHeight = 0f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					floor.add((Floor)obj);
					System.out.println("Founds a floor pixel");
				}
				if (BLOCK_TYPE.WALL_UP.sameColor(currentPixel))
				{
					obj = new WallUp();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_up.add((WallUp)obj);
				}
				else if (BLOCK_TYPE.WALL_DOWN.sameColor(currentPixel))
				{
					obj = new WallDown();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_down.add((WallDown)obj);					
				}
				else if (BLOCK_TYPE.WALL_LEFT.sameColor(currentPixel))
				{
					obj = new WallLeft();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_left.add((WallLeft)obj);
				}
				else if (BLOCK_TYPE.WALL_RIGHT.sameColor(currentPixel))
				{
					obj = new WallRight();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_right.add((WallRight)obj);
				}
				else if (BLOCK_TYPE.KNIGHT.sameColor(currentPixel))
				{
					obj = new Floor();
					offsetHeight = 0f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					floor.add((Floor)obj);
					System.out.println("Founds a floor pixel");
					obj = new Knight();
					offsetHeight = 0f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					knight = (Knight)obj;
				}
				else if (BLOCK_TYPE.WALL_BOTTOMRIGHT.sameColor(currentPixel))
				{
					obj = new WallBottomRight();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_bottomright.add((WallBottomRight)obj);
				}
				else if (BLOCK_TYPE.WALL_TOPRIGHT.sameColor(currentPixel))
				{
					obj = new WallTopRight();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_topright.add((WallTopRight)obj);
				}
				else if (BLOCK_TYPE.WALL_TOPLEFT.sameColor(currentPixel))
				{
					obj = new WallTopLeft();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_topleft.add((WallTopLeft)obj);
				}
				else if (BLOCK_TYPE.WALL_BOTTOMLEFT.sameColor(currentPixel))
				{
					obj = new WallBottomLeft();
					offsetHeight = 0;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wall_bottomleft.add((WallBottomLeft)obj);
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
		for(WallDown wall_down : wall_down)
			wall_down.render(batch);
		for(WallLeft wall_left : wall_left)
			wall_left.render(batch);
		for(WallRight wall_right : wall_right)
			wall_right.render(batch);
		for(WallTopRight wall_topright : wall_topright)
			wall_topright.render(batch);
		for(WallBottomRight wall_bottomright : wall_bottomright)
			wall_bottomright.render(batch);
		for(WallTopLeft wall_topleft : wall_topleft)
			wall_topleft.render(batch);
		for(WallBottomLeft wall_bottomleft : wall_bottomleft)
			wall_bottomleft.render(batch);
	}
	
	public void update(float deltaTime)
	{
		knight.update(deltaTime);
	}
}
