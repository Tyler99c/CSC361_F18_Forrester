package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * This is a knight class
 * @author Tyler Forrester
 *
 */
public class Knight extends AbstractGameObject{

	public enum VIEW_DIRECTION {LEFT, RIGHT, UP, DOWN};
	public static final String TAG = Knight.class.getName();
	public VIEW_DIRECTION viewDirection;
	
	private TextureRegion regKnight;
	
	private final float FLOAT_CYCLE_TIME = 2.0f;
	private final float FLOAT_AMPLITUDE = 0.25f;
	
	private float floatCycleTimeLeft;
	private boolean floatingDownwards;
	private Vector2 floatTargetPositon;
	
	private int length;
	
	private Animation<AtlasRegion> animFront;
	private Animation<AtlasRegion> animBack;
	private Animation<AtlasRegion> animSide;
	private Animation<AtlasRegion> animOtherSide;
	private Animation<AtlasRegion> standFront;
	
	public Knight()
	{
		init();
	}
	
	/**
	 * Makes the knight class
	 */
	public void init()
	{
		dimension.set(1,1);
		
		animFront = Assets.instance.knight.animFront;
		animBack = Assets.instance.knight.animBack;
		animSide = Assets.instance.knight.animSide;
		animOtherSide = Assets.instance.knight.animOtherSide;
		standFront = Assets.instance.knight.standFront;
		regKnight = Assets.instance.knight.knightFront;
		setAnimation(animFront);
		//regKnight = Assets.instance.knight.knight;
		
		//animNormal = Assets.instance.knight.animFront;
		
		bounds.set(0,0,dimension.x,dimension.y);
		origin.set(dimension.x / 2.0f, dimension.y / 2.0f);
	}
	
	/**
	 * Updates the knight
	 */
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		//Has the Player look as if they are walking left
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			if(Gdx.input.isKeyPressed(Keys.W))
			{
				if(animation != animBack) {
				setAnimation(animBack);
				}
			}
			else if(Gdx.input.isKeyPressed(Keys.S))
			{
				setAnimation(animFront);
			}
			else
			{
				if(animation != animOtherSide) 
				{
					setAnimation(animOtherSide);
				}
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.D))
		{
			if(Gdx.input.isKeyPressed(Keys.S))
			{
				if(animation != animFront) 
				{
					setAnimation(animFront);
				}
			}
			else if(Gdx.input.isKeyPressed(Keys.W))
			{
				if(animation != animBack) 
				{
					setAnimation(animBack);
				}
			}
			else
			{
				if(animation != animSide) 
				{
					setAnimation(animSide);
				}
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.S))
		{
			if(animation != animFront) 
			{
				setAnimation(animFront);
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.W))
		{
			if(animation != animBack) 
			{
			setAnimation(animBack);
			}
		}
		else
		{
			
		}
		System.out.println(body.getLinearVelocity());
	}
	
	/**
	 * Draws the knight into the scene
	 */
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		//draw image
		reg = animation.getKeyFrame(stateTime,true);
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(), reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),viewDirection == VIEW_DIRECTION.LEFT, false);
		
	}

}
