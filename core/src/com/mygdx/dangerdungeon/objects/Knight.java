package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

public class Knight extends AbstractGameObject{

	public enum VIEW_DIRECTION {LEFT, RIGHT, UP, DOWN};
	public static final String TAG = Knight.class.getName();
	public VIEW_DIRECTION viewDirection;
	
	private TextureRegion regKnight;
	
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
		
	}
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		//Draw image
		reg = regKnight;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(), reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),viewDirection == VIEW_DIRECTION.LEFT, false);
		
	}

}
