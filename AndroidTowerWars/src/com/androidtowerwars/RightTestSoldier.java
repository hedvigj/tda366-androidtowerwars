package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.androidtowerwars.constants.Constants;

public class RightTestSoldier extends Sprite {
	private BaseGameActivity game;
	private int rSpeed = -70;
	//private int speed = 60; // pixels per second
	public RightTestSoldier(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
	}

	public RightTestSoldier(float pX, float pY, TextureRegion pTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
	}

	public RightTestSoldier(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight, pTextureRegion);
		// TODO Auto-generated constructor stub
	}

	public RightTestSoldier(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
	}

	public void kill() {
		Main.instance.runOnUpdateThread(new Runnable() {
			 public void run() {
			 /* Now it is save to remove the entity! */
				 Main.instance.controller.soldierList.remove(RightTestSoldier.this);
				 Main.instance.getEngine().getScene().getLastChild().detachChild(RightTestSoldier.this);
			 }
			 });
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		setPosition(getX() + rSpeed * pSecondsElapsed, getY());
		
		if (getX() < 200) {
			 //kill();
		}

	}
}
