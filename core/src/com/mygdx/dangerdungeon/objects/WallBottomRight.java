package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

/**
 * Right Wall bottom
 * @author Tyler Forrester
 *
 */
public class WallBottomRight extends AbstractGameObject
{
	private TextureRegion wall;

	
	/**
	 * Creates a new floor instance
	 */
	public WallBottomRight() 
	{
		init();
	}
	
	/**
	 * Initiates the floor class
	 */
	private void init() 
	{
		dimension.set(2.0f,2.0f);
		
		wall = Assets.instance.wall_bottomright.wall_bottomright;
	
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
