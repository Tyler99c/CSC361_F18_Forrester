package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

public class WallDown extends AbstractGameObject
{
	private TextureRegion wall;

	
	/**
	 * Creates a new floor instance
	 */
	public WallDown() 
	{
		init();
	}
	
	/**
	 * Initiates the floor class
	 */
	private void init() 
	{
		dimension.set(1,1);
		
		wall = Assets.instance.wall_down.wall_down;
	
		
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

