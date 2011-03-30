package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.androidtowerwars.constants.Constants;

public class TestSoldier extends Sprite {
	private BaseGameActivity game;
	private int speed = 60; // pixels per second
	public TestSoldier(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
	}

	public TestSoldier(float pX, float pY, TextureRegion pTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
	}

	public TestSoldier(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight, pTextureRegion);
		// TODO Auto-generated constructor stub
	}

	public TestSoldier(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
	}

	public void kill() {
		Main.instance.runOnUpdateThread(new Runnable() {
			 public void run() {
			 /* Now it is save to remove the entity! */
				 Main.instance.controller.soldierList.remove(TestSoldier.this);
				 Main.instance.getEngine().getScene().getLastChild().detachChild(TestSoldier.this);
			 }
			 });
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		setPosition(getX() + speed * pSecondsElapsed, getY());
		
		if (getX() > Constants.MAP_WIDTH - 200) {
			 //kill();
		}

	}
}
