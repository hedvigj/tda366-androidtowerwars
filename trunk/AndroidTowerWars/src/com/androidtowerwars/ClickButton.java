package com.androidtowerwars;

import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class ClickButton extends TiledSprite{
	
	public ClickButton(int pX, float f, TiledTextureRegion mTextureRegion){
		super(pX, f, mTextureRegion);
	}
	
	public void touch(final int mTileIndex){
		this.setCurrentTileIndex(mTileIndex);
	}
	
	public void releaseTouch(final int mTileIndex){
		this.setCurrentTileIndex(mTileIndex);
	}
}
