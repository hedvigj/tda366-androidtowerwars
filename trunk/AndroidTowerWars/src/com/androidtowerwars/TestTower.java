package com.androidtowerwars;

import org.anddev.andengine.entity.primitive.*;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class TestTower extends Sprite {

	private Rectangle range;

	
	public TestTower(float pX, float pY, TextureRegion pTextureRegion, float range) {
		super(pX, pY, pTextureRegion);
		this.range = new Rectangle(pX-(range*0.5f)+this.getWidth()*0.5f, pY-200, range, 400f);
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		for(int n=0;n<Main.instance.controller.soldierList.size();n++) {
			if (Main.instance.controller.soldierList.get(n).collidesWith(range)) {
				Main.instance.controller.soldierList.get(n).kill();
			}
			
		}
		/*for(int n=0;n<Main.instance.controller.rightSoldierList.size();n++){
			if (Main.instance.controller.rightSoldierList.get(n).collidesWith(range)) {
			Main.instance.controller.rightSoldierList.get(n).kill();
		}
		}*/

	}
}
