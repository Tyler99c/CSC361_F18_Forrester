package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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
	
	public Knight()
	{
		init();
	}
	
	
	public void init()
	{
		dimension.set(1,1);
		
		regKnight = Assets.instance.knight.knight;
	}
	
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		
		floatCycleTimeLeft -= deltaTime;
		if (floatCycleTimeLeft<= 0)
		{
			floatCycleTimeLeft = FLOAT_CYCLE_TIME;
			floatingDownwards = !floatingDownwards;
			body.setLinearVelocity(0,FLOAT_AMPLITUDE * (floatingDownwards ? -1 : 1));
		}
		else
		{
			body.setLinearVelocity(body.getLinearVelocity().scl(0.98f));
		}
		
	}
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		//Draw image
		reg = regKnight;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(), reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),viewDirection == VIEW_DIRECTION.LEFT, false);
		
	}

}
