package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.packtpub.libgdx.dangerdungeon.util.Constants;

/**
 * Draws the world and the images in it
 * @author tf0199
 *
 */
public class WorldRenderer implements Disposable
{
	private OrthographicCamera cameraGui;
	private SpriteBatch batch;
	private WorldController worldController;
	
	/**
	 * Creastes the world renderer class our scenes use
	 * @param worldController
	 */
	public WorldRenderer (WorldController worldController) 
	{
		this.worldController = worldController;
		init();
	}
	
	/**
	 * Initializes world Renderer
	 */
	private void init() 
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
		camera.position.set(0,0,0);
		camera.update();
	}
	
	/**
	 * Renders objects
	 */
	public void render() 
	{
		renderWorld(batch);
	}
	
	/**
	 * Renders the test Objects
	 */
	private void renderTestObjects() 
	{
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Sprite sprite : worldController.testSprites)
		{
			sprite.draw(batch);
		}
		batch.end();
	}
	
	/**
	 * Resizes the viewport when changed
	 * @param width
	 * @param height
	 */
	public void resize (int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
	}
	
	/**
	 * Disposes of unused objects to save memory
	 */
	@Override public void dispose() {
		batch.dispose();
	}
	
	private void renderWorld (SpriteBatch batch) 
	{
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		worldController.level.render(batch);
		batch.end();
	}
}
