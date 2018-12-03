package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.dangerdungeon.game.Assets;
import com.badlogic.gdx.math.Vector2;

/**
 * Draws the spikes for the background
 * @author Tyler Forrester
 *
 */
public class Spikes extends AbstractGameObject
{
	private TextureRegion spikes;

	
	/**
	 * Creates a  new spikes instance
	 */
	public Spikes() 
	{
		init();
	}
	
	/**
	 * Initiates the floor class
	 */
	private void init() 
	{
		dimension.set(3,3);
		
		spikes = Assets.instance.levelDecoration.spikes;
	
		bounds.set(0,0,dimension.x,dimension.y);
		origin.set(dimension.x / 2.0f, dimension.y / 2.0f);
	}
	
	/**
	 * We call upon this class to draw the spikes
	 */
	private void drawSpikes(SpriteBatch batch, float parallaxSpeedX)
	{
		TextureRegion reg = null;
		
		//Draw
		reg = spikes;
		batch.setColor(0.5f, 0.5f, 0.5f, 1f);
		batch.draw(reg.getTexture(), position.x * parallaxSpeedX, position.y * parallaxSpeedX, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
		batch.setColor(1, 1, 1, 1);
	}
	
	/**
	 * Draws the object
	 */
	@Override
	public void render(SpriteBatch batch) {
		drawSpikes(batch, 0.8f);
		
	}
	
	/**
	 * Updates position based on camera
	 */
	public void updateScrollPosition (Vector2 camPosition)
	{
		position.set(position.x - camPosition.x,position.y - camPosition.y);
	}

}
