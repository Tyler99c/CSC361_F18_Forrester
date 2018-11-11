package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Is the base for all objects in the game
 * @author Tyler Forrester
 *
 */
public abstract class AbstractGameObject 
{
	
	public Body body;
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	public Rectangle bounds;
	
	
	
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
	 * Allows the objects to update
	 * @param deltaTime
	 */
	public void update (float deltaTime)
	{
		position.set(body.getPosition());
		rotation = body.getAngle() * MathUtils.radiansToDegrees;
	}
	/**
	 * Draws the game object
	 * @param batch
	 */
	public abstract void render (SpriteBatch batch);
}
