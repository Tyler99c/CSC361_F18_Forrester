package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.packtpub.libgdx.dangerdungeon.util.CameraHelper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.dangerdungeon.objects.Floor;
import com.packtpub.libgdx.dangerdungeon.util.Constants;

/**
 * Class that Handles the inputs in the world
 * @author Tyler Forrester
 *
 */
public class WorldController extends InputAdapter
{
	private static final String TAG = WorldController.class.getName();
	public Sprite[] testSprites;
	public int selectedSprite;
	public CameraHelper cameraHelper;
	public Level level;
	public int health;
	public int score;
	
	/**
	 * Cresates the worldController instance
	 */
	public WorldController() 
	{
		init();
	}
	
	/**
	 * initiates the level
	 */
	private void initLevel()
	{
		score = 0;
		level = new Level(Constants.LEVEL_01);
	}
	
	/**
	 * Starts the world controller
	 */
	private void init() 
	{
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initTestObjects();
		initLevel();
	}
	
	/**
	 * Creates test objects for debugging purposes
	 */
	private void initTestObjects() 
	{
		//Cr4eate new array for 5 sprites
		testSprites = new Sprite[5];
		//create a list of texture regions
		Array<TextureRegion> regions = new Array<TextureRegion>();
		regions.add(Assets.instance.knight.knight);
		regions.add(Assets.instance.chest.chest);
		//Create new sprites using the just created texture
		for (int i = 0; i < testSprites.length; i++)
		{
			Sprite spr = new Sprite(regions.random());
			//Define sprite size to be 1m x 1m in game world
			spr.setSize(1,1);
			//Set origin to sprite's center
			spr.setOrigin(spr.getWidth()/2.0f, spr.getHeight()/2.0f);
			//Calculate random position for sprite
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f,2.0f);
			spr.setPosition(randomX, randomY);
			//Put new Sprite into array
			testSprites[i] = spr;
		}
		//Set first sprite as selected one
		selectedSprite = 0;
	}
	/**
	 * Creaste an object to test with
	 * @param width
	 * @param height
	 * @return
	 */
	private Pixmap createProceduralPixmap (int width, int height) 
	{
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		//Fill square with red color at 50% opacity
		pixmap.setColor(1,0,0,0.5f);
		pixmap.fill();
		//Draw a yellow-colored X shap on square
		pixmap.setColor(1,1,0,1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		//Draw a cayan-colored border around square
		pixmap.setColor(0,1,1,1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}
	
	/**
	 * Updates the scene when called upon
	 * @param deltaTime
	 */
	public void update (float deltaTime) 
	{
		handleDebugInput(deltaTime);
		//updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}
	
	/**
	 * Handles User input when using the debug settings
	 * @param deltaTime
	 */
	private void handleDebugInput (float deltaTime)
	{
		if (Gdx.app.getType() != ApplicationType.Desktop) return;
		
		//Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) 
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT)) 
			moveCamera(-camMoveSpeed,0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveCamera(camMoveSpeed,0);
		if (Gdx.input.isKeyPressed(Keys.UP))
			moveCamera(0,camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			moveCamera(0,-camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
			cameraHelper.setPosition(0,0);
	}
	
	/**
	 * Moves the Camera
	 * @param x
	 * @param y
	 */
	private void moveCamera (float x, float y)
	{
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x,y);
	}
	
	/**
	 * Moves the currently selected sprite
	 * @param x
	 * @param y
	 */
	private void moveSelectedSprite(float x, float y) 
	{
		testSprites[selectedSprite].translate(x,y);
	}
	
	/**
	 * Performs an action when one of these keys is let up
	 */
	@Override
	public boolean keyUp(int keycode)
	{
		//Reset game world
		if (keycode == Keys.R) {
			//Reset game world
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		}
		return false;
	}
}
