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
	private int height;
	private int length;
	
	
	
	/**
	 * Creates a  new spikes instance
	 */
	public Spikes(int height, int width) 
	{
		this.height = height;
		this.length = width;
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
		reg = spikes;
		batch.setColor(0.5f, 0.5f, 0.5f, 1f);
		
		float xRel = dimension.x;
		float yRel = dimension.y;

		// mountains span the whole level
		int mountainLength = 0;
		mountainLength += MathUtils.ceil(length / (2 * dimension.x) * (1 - parallaxSpeedX));
		int mountainHeight = 0;
		mountainHeight += MathUtils.ceil(height / (2 * dimension.y) * (1-parallaxSpeedX));
		for(int j = 0; j < mountainHeight; j++)
		{
			for (int i = 0; i < mountainLength; i++)
			{
			
				batch.draw(reg.getTexture(), origin.x + xRel + position.x * parallaxSpeedX, origin.y + yRel + position.y,
						origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
						reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
				xRel += dimension.x;
				System.out.println("Spike Drawn");
			}
			yRel += dimension.y;
		}
		
		//Draw
		//reg = spikes;
		//batch.setColor(0.5f, 0.5f, 0.5f, 1f);
		//batch.draw(reg.getTexture(), position.x * parallaxSpeedX, position.y * parallaxSpeedX, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
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
		//camPosition.x = camPosition.x - position.x;
		//camPosition.y = camPosition.y - position.y;
		//position.set(camPosition.x, camPosition.y); 

	}

}
