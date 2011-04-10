package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.androidtowerwars.GameActivity;

public class TestTower implements ITower {

	private Rectangle range;
	private World.Team team;

	
	public TestTower(float pX, float pY, float range, World.Team team) {
		//this.range = new Rectangle(pX-(range*0.5f)+this.getWidth()*0.5f, pY-200, range, 400f);
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-200, range, 400f); //not centered by width!
		this.team = team;
	}

	protected void onManagedUpdate(float pSecondsElapsed) {
		//super.onManagedUpdate(pSecondsElapsed);
//		for(int n=0;n<GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).size();n++) {
//			if (range.collidesWith(GameActivity.instance.controller.soldierListMap.get(team.opposite()).get(n))) {
//				GameActivity.instance.controller.soldierListMap.get(team.opposite()).get(n).die();
//			}
//		}
	}

	public World.Team getTeam() {
		return team;
	}
	public int getAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int sellTower() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setAttack() {
		// TODO Auto-generated method stub
		
	}

	public void upgradeTower() {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getRange() {
		return range;
	}
}
