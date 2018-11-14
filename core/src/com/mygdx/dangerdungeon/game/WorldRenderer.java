package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.packtpub.libgdx.dangerdungeon.util.Constants;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

/**
 * Draws the world and the images in it
 * @author tf0199
 *
 */
public class WorldRenderer implements Disposable
{
	private OrthographicCamera cameraGui;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;
	private static final boolean DEBUG_DRAW_BOX2D_WORLD = false;
	private Box2DDebugRenderer b2debugRenderer;
	
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
		cameraGui = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT);
		cameraGui.position.set(0,0,0);
		cameraGui.setToOrtho(true);
		cameraGui.update();
		b2debugRenderer = new Box2DDebugRenderer();
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
		cameraGui.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		cameraGui.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width;
		cameraGui.position.set(cameraGui.viewportWidth/2, cameraGui.viewportHeight /2,0);
		cameraGui.update();
		
	}
	
	/**
	 * Disposes of unused objects to save memory
	 */
	@Override public void dispose() {
		batch.dispose();
	}
	
	/**
	 * Draws the world
	 * @param batch
	 */
	private void renderWorld (SpriteBatch batch) 
	{
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		worldController.level.render(batch);
		batch.end();
		if (DEBUG_DRAW_BOX2D_WORLD)
		{
			b2debugRenderer.render(worldController.b2world, camera.combined);
		}
	}
}
