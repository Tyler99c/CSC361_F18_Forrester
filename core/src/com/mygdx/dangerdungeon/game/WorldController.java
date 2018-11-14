package com.mygdx.dangerdungeon.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.packtpub.libgdx.dangerdungeon.util.CameraHelper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.dangerdungeon.objects.Floor;
import com.mygdx.dangerdungeon.objects.Knight;
import com.mygdx.dangerdungeon.objects.WallBottomLeft;
import com.mygdx.dangerdungeon.objects.WallBottomRight;
import com.mygdx.dangerdungeon.objects.WallDown;
import com.mygdx.dangerdungeon.objects.WallLeft;
import com.mygdx.dangerdungeon.objects.WallRight;
import com.mygdx.dangerdungeon.objects.WallTopLeft;
import com.mygdx.dangerdungeon.objects.WallTopRight;
import com.mygdx.dangerdungeon.objects.WallUp;
import com.packtpub.libgdx.dangerdungeon.util.Constants;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
	
	private boolean goalReached;
	public World b2world;
	
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
		cameraHelper.setTarget(level.knight);
		initPhysics();
	}
	
	private void initPhysics() 
	{
		if(b2world != null)
		{
			b2world.dispose();
		}
		
		b2world = new World(new Vector2(0,0), true);
		
		
		Vector2 origin = new Vector2();
		Knight knight = level.knight;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(knight.position);
		Body body = b2world.createBody(bodyDef);
		knight.body = body;
		PolygonShape polygonShape = new PolygonShape();
		origin.x = knight.bounds.width / 2.0f;
		origin.y = knight.bounds.height / 2.0f;
		polygonShape.setAsBox(knight.bounds.width / 2.0f, knight.bounds.height / 2.0f,origin,0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.restitution = 0f;
		body.createFixture(fixtureDef);
		polygonShape.dispose();
		
		for(WallUp wall_up : level.wall_up)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_up.position);
			body = b2world.createBody(bodyDef);
			wall_up.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_up.bounds.width /2.0f;
			origin.y = wall_up.bounds.height/2.0f;
			polygonShape.setAsBox(wall_up.bounds.width/2.0f,wall_up.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();
		}
		for(WallDown wall_down : level.wall_down)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_down.position);
			body = b2world.createBody(bodyDef);
			wall_down.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_down.bounds.width /2.0f;
			origin.y = wall_down.bounds.height/2.0f;
			polygonShape.setAsBox(wall_down.bounds.width/2.0f,wall_down.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();
		}
		for(WallRight wall_right : level.wall_right)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_right.position);
			body = b2world.createBody(bodyDef);
			wall_right.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_right.bounds.width /2.0f + .4f;
			origin.y = wall_right.bounds.height/2.0f;
			polygonShape.setAsBox(wall_right.bounds.width/2.0f,wall_right.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();

		}
		for(WallLeft wall_left : level.wall_left)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_left.position);
			body = b2world.createBody(bodyDef);
			wall_left.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_left.bounds.width /2.0f - .4f;
			origin.y = wall_left.bounds.height/2.0f;
			polygonShape.setAsBox(wall_left.bounds.width/2.0f,wall_left.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();

		}
		for(WallBottomLeft wall_bottomleft : level.wall_bottomleft)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_bottomleft.position);
			body = b2world.createBody(bodyDef);
			wall_bottomleft.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_bottomleft.bounds.width /2.0f + .5f;
			origin.y = wall_bottomleft.bounds.height/2.0f;
			polygonShape.setAsBox(wall_bottomleft.bounds.width/2.0f,wall_bottomleft.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();

		}
		for(WallBottomRight wall_bottomright : level.wall_bottomright)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_bottomright.position);
			body = b2world.createBody(bodyDef);
			wall_bottomright.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_bottomright.bounds.width /2.0f -.5f;
			origin.y = wall_bottomright.bounds.height/2.0f;
			polygonShape.setAsBox(wall_bottomright.bounds.width/2.0f,wall_bottomright.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();

		}
		for(WallTopRight wall_topright : level.wall_topright)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_topright.position);
			body = b2world.createBody(bodyDef);
			wall_topright.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_topright.bounds.width /2.0f - .5f;
			origin.y = wall_topright.bounds.height/2.0f;
			polygonShape.setAsBox(wall_topright.bounds.width/2.0f,wall_topright.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();

		}
		for(WallTopLeft wall_topleft : level.wall_topleft)
		{
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(wall_topleft.position);
			body = b2world.createBody(bodyDef);
			wall_topleft.body = body;
			polygonShape = new PolygonShape();
			origin.x = wall_topleft.bounds.width /2.0f + .5f;
			origin.y = wall_topleft.bounds.height/2.0f;
			polygonShape.setAsBox(wall_topleft.bounds.width/2.0f,wall_topleft.bounds.height/2.0f,origin,0);
			fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			body.createFixture(fixtureDef);
			polygonShape.dispose();

		}
		
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
		handleInputGame(deltaTime);
		handleDebugInput(deltaTime);
		//updateTestObjects(deltaTime);
		level.update(deltaTime);
		b2world.step(deltaTime, 8, 3);
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
	
	private void handleInputGame(float deltaTime)
	{
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			if(Gdx.input.isKeyPressed(Keys.W))
			{
				level.knight.body.setLinearVelocity(-5,5);
			}
			else if(Gdx.input.isKeyPressed(Keys.S))
			{
				level.knight.body.setLinearVelocity(-5,-5);
			}
			else
			{
				level.knight.body.setLinearVelocity(-5,0);
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.D))
		{
			if(Gdx.input.isKeyPressed(Keys.S))
			{
				level.knight.body.setLinearVelocity(5,-5);
			}
			else if(Gdx.input.isKeyPressed(Keys.W))
			{
				level.knight.body.setLinearVelocity(5,5);
			}
			else
			{
			level.knight.body.setLinearVelocity(5,0);
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.S))
		{
			level.knight.body.setLinearVelocity(0,-5);
		}
		else if (Gdx.input.isKeyPressed(Keys.W))
		{
			level.knight.body.setLinearVelocity(0,5);
		}
		else
		{
			level.knight.body.setLinearVelocity(0,0);
		}
	}
}
