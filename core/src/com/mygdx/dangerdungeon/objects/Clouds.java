package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.dangerdungeon.game.Assets;

/**
 *  Creates the floor unwhich the player walk, mearly a decoration
 * @author Tyler Forrester
 *
 */
public class Clouds extends AbstractGameObject
{
	private Texture tile;

	
	/**
	 * Creates a new floor instance
	 */
	public Clouds() 
	{
		init();
	}
	
	/**
	 * Initiates the floor class
	 */
	private void init() 
	{
		dimension.set(1f,1f);
		
		tile = Assets.instance.background.background;
	
		
	}
	
	/**
	 * Draws the object
	 */
	@Override
	public void render(SpriteBatch batch) {
		Texture reg = null;
		
		//Draw
		reg = tile;
		batch.draw(reg, position.x*0.8f, position.y*0.8f);
		//batch.draw(reg, position.x*0.8f, position.y*0.8f, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y);
	}
	
	/**
	 * Updates position based on camera
	 */
	public void updateScrollPosition (Vector2 camPosition)
	{
		//camPosition.x = camPosition.x - position.x;
		//camPosition.y = camPosition.y - position.y;
		position.set(camPosition.x-20.0f, camPosition.y-20.0f); 

	}

}

