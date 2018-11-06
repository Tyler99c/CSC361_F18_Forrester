package com.mygdx.dangerdungeon.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dangerdungeon.game.Assets;

public class Floor extends AbstractGameObject
{
	private TextureRegion tile;

	private int length;
	
	public Floor() 
	{
		init();
	}
	
	private void init() 
	{
		dimension.set(1,1);
		
		tile = Assets.instance.floor.floor;
	
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		//Draw
		reg = tile;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
	}

}
