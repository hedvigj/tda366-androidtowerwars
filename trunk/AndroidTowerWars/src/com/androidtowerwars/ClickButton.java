package com.androidtowerwars;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class ClickButton extends Sprite{
	
	public ClickButton(float pX, float pY, TextureRegion mTextureRegion){
		super(pX, pY, mTextureRegion);
	}
	
	public void touch(final int mTileIndex){
		
	}
	
	public void releaseTouch(final int f){
	}

}
