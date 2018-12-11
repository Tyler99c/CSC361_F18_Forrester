package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.dangerdungeon.game.Assets;

/**
 *  Creates the chest which the player picks up to collect score
 * @author Tyler Forrester
 *
 */
public class Chest extends AbstractGameObject
{
	private TextureRegion tile;
	

	
	/**
	 * Creates a new chest instance
	 */
	public Chest() 
	{
		init();
	}
	
	/**
	 * Initiates the chest class
	 */
	private void init() 
	{
		dimension.set(1,1);
		
		tile = Assets.instance.chest.chest;
		
		bounds.set(0,0,dimension.x,dimension.y);
		origin.set(dimension.x / 2.0f, dimension.y / 2.0f);
	}
	
	/**
	 * Draws the object
	 */
	@Override
	public void render(SpriteBatch batch) 
	{
		TextureRegion reg = null;
		
			//Draw
		if(destroyed == false) 
		{
			reg = tile;
			batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
		}
	}

}
