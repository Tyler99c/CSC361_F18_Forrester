package com.packtpub.libgdx.dangerdungeon.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.dangerdungeon.objects.AbstractGameObject;
import com.mygdx.dangerdungeon.objects.Knight;

/**
 * Lets us move and update the camera
 * @author Tyler Forrester
 *
 */
public class CameraHelper 
{
	private static final String TAG = CameraHelper.class.getName();
	
	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;
	
	private Vector2 position;
	private float zoom;
	private AbstractGameObject target;
	
	/**
	 * Makes a Camera Helper Instance
	 */
	public CameraHelper()
	{
		position = new Vector2();
		zoom = 0.75f;
	}
	
	/**
	 * Updates the camera's position
	 * @param deltaTime
	 */
	public void update (float deltaTime)
	{
		if (!hasTarget()) return;
		
		position.x = target.position.x + target.origin.x;
		position.y = target.position.y + target.origin.y;
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
	public void setTarget (AbstractGameObject target)
	{
		this.target = target;
	}
	
	/**
	 * Gets the current target of the camera
	 * @return
	 */
	public AbstractGameObject getTarget()
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
	public boolean hasTarget(AbstractGameObject target)
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
