package com.packtpub.libgdx.dangerdungeon.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Lets us move and update the camera
 * @author Tyler99c
 *
 */
public class CameraHelper 
{
	private static final String TAG = CameraHelper.class.getName();
	
	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;
	
	private Vector2 position;
	private float zoom;
	private Sprite target;
	
	/**
	 * Makes a Camera Helper Instance
	 */
	public CameraHelper()
	{
		position = new Vector2();
		zoom = 1.0f;
	}
	
	/**
	 * Updates the camera's position
	 * @param deltaTime
	 */
	public void update (float deltaTime)
	{
		if (!hasTarget()) return;
		
		position.x = target.getX() + target.getOriginX();
		position.y = target.getY() + target.getOriginY();
	}
	
	/**
	 * Sets teh camera's positon
	 * @param x
	 * @param y
	 */
	public void setPosition (float x, float y)
	{
		this.position.set(x,y);
	}
	
	/**
	 * Gets the camera's current position
	 * @return
	 */
	public Vector2 getPosition() 
	{
		return position;
	}
	
	/**
	 * Adds zoom to zoom in
	 * @param ammount
	 */
	public void addZoom (float ammount)
	{
		setZoom(zoom + ammount);
	}
	
	/**
	 * Sets the camera's zoom
	 * @param zoom
	 */
	public void setZoom(float zoom)
	{
		this.zoom = MathUtils.clamp(zoom,  MAX_ZOOM_IN,  MAX_ZOOM_OUT);
	}
	
	/**
	 * Get's the camera's current zoom
	 * @return
	 */
	public float getZoom()
	{
		return zoom;
	}
	
	/**
	 * Sets the camera's target to follow
	 * @param target
	 */
	public void setTarget (Sprite target)
	{
		this.target = target;
	}
	
	/**
	 * Gets the current target of the camera
	 * @return
	 */
	public Sprite getTarget()
	{
		return target;
	}
	
	/**
	 * Checks to see if the camera has a target
	 * @return
	 */
	public boolean hasTarget()
	{
		return target != null;
	}
	
	/**
	 * Sees if a sprite and the camera's target is the same
	 * @param target
	 * @return
	 */
	public boolean hasTarget(Sprite target)
	{
		return hasTarget() && this.target.equals(target);
	}
	
	/**
	 * Sets camera's positon, zoom, and updates it
	 * @param camera
	 */
	public void applyTo (OrthographicCamera camera)
	{
		camera.position.x = position.x;
		camera.position.y = position.y;
		camera.zoom = zoom;
		camera.update();
	}
	

}
