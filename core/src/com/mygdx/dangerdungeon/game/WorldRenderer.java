package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.dangerdungeon.game.Assets;
import com.packtpub.libgdx.dangerdungeon.util.GamePreferences;
import com.packtpub.libgdx.dangerdungeon.util.Constants;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

/**
 * Draws the world and the images in it
 * @author Tyler Forreter
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
	 * Responsible for rendering extra lives
	 * 
	 * @param batch
	 *            Spritebatch object being used to draw
	 */
	private void renderGuiExtraLive(SpriteBatch batch)
	{
		float x = cameraGui.viewportWidth - 50 - Constants.LIVES_START * 50;
		float y = -15;
		for (int i = 0; i < Constants.LIVES_START; i++)
		{
			if (worldController.lives <= i)
				batch.setColor(0.5f, 0.5f, 0.5f, 0.5f);

			batch.draw(Assets.instance.heart.heart, x + i * 50, y, 50, 50, 120, 100, 0.35f, -0.35f, 0);
			batch.setColor(1, 1, 1, 1);
		}

		if (worldController.lives >= 0 && worldController.lives > worldController.lives)
		{
			int i = worldController.lives;
			float alphaColor = Math.max(0, worldController.livesVisual - worldController.lives - 0.5f);
			float alphaScale = 0.35f * (2 + worldController.lives - worldController.livesVisual) * 2;
			float alphaRotate = -45 * alphaColor;
			batch.setColor(1.0f, 0.7f, 0.7f, alphaColor);
			batch.draw(Assets.instance.chest.chest, x + i * 50, y, 50, 50, 120, 100, alphaScale, -alphaScale,
					alphaRotate);
			batch.setColor(1, 1, 1, 1);
		}
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
	
	/**
	 * Renders how long the player has the statue powerup
	 * @param batch
	 */
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
	 * Displays the current highscores
	 */
	public void renderGuiHigh (SpriteBatch batch)
	{
		float x = -15;
		float y = -15;
		GamePreferences prefs = GamePreferences.instance;
		prefs.load();
		Assets.instance.fonts.defaultBig.draw(batch, "HighScore: " + prefs.highscore[0], x + 200, y + 37);
		boolean displayHigh = worldController.displayHigh;
		if(displayHigh)
		{
			for(int i = 1; i < 10; i++)
			{
				Assets.instance.fonts.defaultBig.draw(batch, "" + prefs.highscore[i], x + 200, y + 37 + (i * 30));
			}
		}
	}
	
	/**
	 * Renders a game over message on the screen when called
	 * 
	 * @param batch
	 *            the Spritebatch object being used to draw
	 */
	private void renderGuiGameOverMessage(SpriteBatch batch)
	{
		float x = cameraGui.viewportWidth / 2;
		float y = cameraGui.viewportHeight / 2;
		if (worldController.isGameOver())
		{
			BitmapFont fontGameOver = Assets.instance.fonts.defaultBig;
			fontGameOver.setColor(1, 0.75f, 0.25f, 1);
			fontGameOver.draw(batch, "GAME OVER", x, y, 1, Align.center, false);
			fontGameOver.setColor(1, 1, 1, 1);
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
		renderGuiHigh(batch);
		renderGuiExtraLive(batch);
		renderGuiGameOverMessage(batch);
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
