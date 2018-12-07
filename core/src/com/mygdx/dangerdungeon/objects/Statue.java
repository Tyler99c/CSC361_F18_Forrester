package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

/**
 *  Creates the floor unwhich the player walk, mearly a decoration
 * @author Tyler Forrester
 *
 */
public class Statue extends AbstractGameObject
{
	private TextureRegion tile;

	
	/**
	 * Creates a new floor instance
	 */
	public Statue() 
	{
		init();
	}
	
	/**
	 * Initiates the floor class
	 */
	private void init() 
	{
		dimension.set(1f,1f);
		
		tile = Assets.instance.statue.statue;
	
		
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
		if(destroyed == false) 
		{
			reg = tile;
			batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x/2.0f, scale.y/2.0f, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
		}
	}

}