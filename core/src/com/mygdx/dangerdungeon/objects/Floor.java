package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

/**
 *  Creates the floor unwhich the player walk, mearly a decoration
 * @author Tyler Forrester
 *
 */
public class Floor extends AbstractGameObject
{
	private TextureRegion tile;

	private int length;
	
	/**
	 * Creates a new floor instance
	 */
	public Floor() 
	{
		init();
	}
	
	/**
	 * Initiates the floor class
	 */
	private void init() 
	{
		dimension.set(1,1);
		
		tile = Assets.instance.floor.floor;
	
		
	}
	
	/**
	 * Draws the object
	 */
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		//Draw
		reg = tile;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
	}

}
