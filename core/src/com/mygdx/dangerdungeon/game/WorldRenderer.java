package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.dangerdungeon.game.Assets;
import com.packtpub.libgdx.dangerdungeon.util.GamePreferences;
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
	private static final boolean DEBUG_DRAW_BOX2D_WORLD = true;
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
	 * Renders the Player's current score
	 */
	public void renderGuiScore (SpriteBatch batch)
	{
		float x = -15;
		float y = -15;
		batch.draw(Assets.instance.chest.chest,x,y,50,50,100,100,0.35f,-0.35f,0);
		Assets.instance.fonts.defaultBig.draw(batch, "" + worldController.score, x + 75, y + 37);
	
	}
	
	/**
	 * Renders the fps counter
	 */
	public void renderGuiFPS (SpriteBatch batch)
	{
		float x = cameraGui.viewportWidth - 55;
		float y = cameraGui.viewportHeight - 15;
		int fps = Gdx.graphics.getFramesPerSecond();
		BitmapFont fpsFont = Assets.instance.fonts.defaultNormal;
		if (fps >= 45)
		{
			// 45 or more FPS show up in green
			fpsFont.setColor(0, 1, 0, 1);
		} else if (fps >= 30)
		{
			// 30 or more FPS show up in yellow
			fpsFont.setColor(1, 1, 0, 1);
		} else
		{
			// less than 30 FPS show up in red
			fpsFont.setColor(1, 0, 0, 1);
		}
		fpsFont.draw(batch, "FPS: " + fps, x, y);
		fpsFont.setColor(1, 1, 1, 1); // white
	}
	
	private void renderGuiStatue(SpriteBatch batch)
	{
		float x = -15;
		float y = 30;
		float timeLeftFeatherPowerup = worldController.level.knight.timeLeftStatue;

		if (timeLeftFeatherPowerup > 0)
		{
			/*
			 * start icon fade in/out if the left power-up time is less than 4 seconds. The
			 * fade interval is set to 5 changes per second
			 */
			if (timeLeftFeatherPowerup < 4)
			{
				if (((int) (timeLeftFeatherPowerup * 5) % 2) != 0)
				{
					batch.setColor(1, 1, 1, 0.5f);
				}
			}

			batch.draw(Assets.instance.statue.statue, x, y, 50, 50, 100, 100, 0.35f, -0.35f, 0);
			batch.setColor(1, 1, 1, 1);
			Assets.instance.fonts.defaultSmall.draw(batch, "" + (int) timeLeftFeatherPowerup, x + 60, y + 57);
		}
	}
	
	/**
	 * Rends the gui
	 * @param batch
	 */
	private void renderGui(SpriteBatch batch)
	{
		batch.setProjectionMatrix(cameraGui.combined);
		batch.begin();
		// draw collected gold coins icokn + text
		// (anchored to top left edge)
		renderGuiScore(batch);
		renderGuiFPS(batch);
		renderGuiStatue(batch);
		batch.end();
	}
	
	/**
	 * Renders objects
	 */
	public void render() 
	{
		renderWorld(batch);
		renderGui(batch);
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
