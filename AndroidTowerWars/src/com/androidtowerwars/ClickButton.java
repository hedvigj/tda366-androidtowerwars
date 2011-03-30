package com.androidtowerwars;

import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class ClickButton extends TiledSprite{
	
	public ClickButton(int pX, int pY, TiledTextureRegion mTextureRegion){
		super(pX, pY, mTextureRegion);
	}
	
	public void touch(final int mTileIndex){
		this.setCurrentTileIndex(mTileIndex);
	}
	
	public void releaseTouch(final int mTileIndex){
		this.setCurrentTileIndex(mTileIndex);
	}
}