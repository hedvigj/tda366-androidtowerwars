package com.androidtowerwars;

import org.anddev.andengine.entity.primitive.*;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class BadTower extends Sprite {

	private Rectangle range;
	
	public BadTower(float pX, float pY, TextureRegion pTextureRegion, float range) {
		super(pX, pY, pTextureRegion);
		this.range = new Rectangle(pX-(range*0.5f)+this.getWidth()*0.5f, pY-50, range, 100f);
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		
			
		for(int n=0;n<Main.instance.controller.rightSoldierList.size();n++){
			if (Main.instance.controller.rightSoldierList.get(n).collidesWith(range)) {
			Main.instance.controller.rightSoldierList.get(n).kill();
		}
		}

	}

}
