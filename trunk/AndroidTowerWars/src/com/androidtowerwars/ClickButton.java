package com.androidtowerwars;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class ClickButton extends Sprite{
	private boolean isPressed = false;
	
	
	public ClickButton(float pX, float pY, TextureRegion mTextureRegion){
		super(pX, pY, mTextureRegion);
	}
	
	public void touch(){
		isPressed = true;
	}
	
	public boolean releaseTouchSuccessfull(){
		if (isPressed) {
			isPressed=false;
			return true;
		}
		return false;
	}

}
