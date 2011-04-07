package com.androidtowerwars;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class ClickButton extends Sprite{
	
	public ClickButton(int pX, float f, TextureRegion mTextureRegion){
		super(pX, f, mTextureRegion);
	}
	
	public void touch(final int mTileIndex){
	}
	
	public void releaseTouch(final int f){
	}
}
