package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Is the base for all objects in the game
 * @author tf0199
 *
 */
public abstract class AbstractGameObject 
{

	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	
	
	/**
	 * Instances an object with dimensions, scale and positon
	 */
	public AbstractGameObject()
	{
		position = new Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale = new Vector2(1,1);
		rotation = 0;
	}
	
	/**
	 * Allows the objects
	 * @param deltaTime
	 */
	public void update (float deltaTime)
	{
		
	}

	public abstract void render (SpriteBatch batch);
}
