package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.androidtowerwars.GameActivity;

public class TestTower extends Sprite {

	private Rectangle range;
	private World.Team team;

	
	public TestTower(float pX, float pY, TextureRegion pTextureRegion, float range, World.Team team) {
		super(pX, pY, pTextureRegion);
		this.range = new Rectangle(pX-(range*0.5f)+this.getWidth()*0.5f, pY-200, range, 400f);
		this.team = team;
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		for(int n=0;n<GameActivity.instance.controller.soldierListMap.get(team.opposite()).size();n++) {
			if (GameActivity.instance.controller.soldierListMap.get(team.opposite()).get(n).collidesWith(range)) {
				GameActivity.instance.controller.soldierListMap.get(team.opposite()).get(n).kill();
			}
			
		}

	}
}
