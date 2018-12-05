package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	public float stateTime;
	public Animation<AtlasRegion> animation;
	
	
	/**
	 * Instances an object with dimensions, scale and positon
	 */
	public AbstractGameObject()
	{
		position = new Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale = new Vector2(1,1);
		bounds = new Rectangle();
		rotation = 0;
	}
	
	/**
	 * Updates the motion of an object
	 * @param deltaTime
	 */
	protected void updateMotion(float deltaTime)
	{
	}
	
	/**
	 * Allows the objects to update
	 * @param deltaTime
	 */
	public void update (float deltaTime)
	{
		stateTime += deltaTime;
		position.set(body.getPosition());
		rotation = body.getAngle() * MathUtils.radiansToDegrees;
		updateMotion(deltaTime);
	}
	/**
	 * Draws the game object
	 * @param batch
	 */
	public abstract void render (SpriteBatch batch);
	
	/**
	 * Sets the animtion for an object
	 */
	public void setAnimation(Animation animation)
	{
		this.animation = animation;
		stateTime = 0;
	}
	
}
