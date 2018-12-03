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

	private TextureRegion regSpikes;

	private int length;
	private int height;

	/**
	 * Constructor that calls init, keeps track of the background's width and length
	 */
	public Spikes(int length,int height)
	{
		this.length = length;
		this.height = height;
		init();
	}

	/**
	 * Initailizes spikes
	 */
	private void init()
	{
		dimension.set(1, 1);

		regSpikes = Assets.instance.levelDecoration.spikes;

		// shift mountain and extend length
		origin.x = -dimension.x * 2;
		length += dimension.x * 2;
	}

	/**
	 * Draws the spikes on the background
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 * @param tintColor
	 * @param parallaxSpeedX
	 */
	private void drawSpikes(SpriteBatch batch, float offsetX, float offsetY, float tintColor, float parallaxSpeedX)
	{
		TextureRegion reg = regSpikes;
		batch.setColor(tintColor, tintColor, tintColor, 1);
		float xRel = dimension.x * offsetX;
		float yRel = dimension.y * offsetY;

		// mountains span the whole level
		int spikeLength = 0;
		spikeLength += MathUtils.ceil(length / (2 * dimension.x) * (1 - parallaxSpeedX));
		spikeLength += MathUtils.ceil(0.5f + offsetX);
		
		int spikeHeight = 0;
		spikeHeight += MathUtils.ceil(length / (2 * dimension.x) * (1 - parallaxSpeedX));
		spikeHeight += MathUtils.ceil(0.5f + offsetX);
		/*for (int i = 0; i < spikeLength; i++)
		{
			for(int j = 0; j < spikeHeight; j++)
			{
				// spikes
				reg = regMountainLeft;
				batch.draw(reg.getTexture(), origin.x + xRel + position.x * parallaxSpeedX, origin.y + yRel + position.y,
						origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
						reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
				xRel += dimension.x;
			}
		}*/
		/*batch.draw(reg.getTexture(), origin.x + xRel + position.x * parallaxSpeedX, origin.y + yRel + position.y,
				origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
				reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);*/

		// reset color to white
		batch.setColor(1, 1, 1, 1);
	}

	/**
	 * Calls the draw mountain method, has 3 tints
	 * 
	 * @param batch
	 */
	@Override
	public void render(SpriteBatch batch)
	{
		// distant spikes (dark gray)
		drawSpikes(batch, 0.5f, 0.5f, 0.5f, 0.8f);
	}

	/**
	 * Updates the scrolling of the mountains
	 */
	public void updateScrollPosition(Vector2 camPosition)
	{
		position.set(camPosition.x, position.y);
	}
}
