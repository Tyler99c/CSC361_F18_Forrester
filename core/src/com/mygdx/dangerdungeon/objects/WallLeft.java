package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

/**
 * Left Wall 
 * @author Tyler Forrester
 *
 */
public class WallLeft extends AbstractGameObject
{
	private TextureRegion wall;

	
	/**
	 * Creates a new wall instance
	 */
	public WallLeft() 
	{
		init();
	}
	
	/**
	 * Initiates the wall class
	 */
	private void init() 
	{
		dimension.set(1,1);
		
		wall = Assets.instance.wall_left.wall_left;
	
		bounds.set(0,0,dimension.x,dimension.y);
		origin.set(dimension.x / 2.0f, dimension.y / 2.0f);
	}
	
	/**
	 * Draws the object
	 */
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		//Draw
		reg = wall;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
	}

}
