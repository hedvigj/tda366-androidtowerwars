package com.androidtowerwars.model;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.androidtowerwars.GameActivity;

public class TestSoldier extends Sprite {
	private BaseGameActivity game;
	private int speed = 60; // pixels per second
	private World.Team team;
	public TestSoldier(float pX, float pY, TextureRegion pTextureRegion, World.Team team) {
		super(pX, pY, pTextureRegion);
		this.team = team;
		if (team == World.Team.GOOD) {
			speed = -speed;
		}
	}

	public void kill() {
		GameActivity.instance.runOnUpdateThread(new Runnable() {
			 public void run() {
			 /* Now it is save to remove the entity! */
				 GameActivity.instance.controller.soldierListMap.get(team).remove(TestSoldier.this);
				 GameActivity.instance.getEngine().getScene().getLastChild().detachChild(TestSoldier.this);
			 }
			 });
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		setPosition(getX() + speed * pSecondsElapsed, getY());

	}
}
