package com.androidtowerwars;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

import com.androidtowerwars.constants.Constants;

public class TestSoldier extends Sprite {

	private int speed = 50; //20 pixels per second
	
	public TestSoldier(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		// TODO Auto-generated constructor stub
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

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		setPosition(getX()+speed*pSecondsElapsed,getY());
		if(getX()>Constants.MAP_WIDTH-200) {
//			this.runOnUpdateThread(new Runnable() {
//                public void run() {
//                    /* Now it is save to remove the entity! */
//                	getParent().getLastChild().detachChild(this);
//                }
//        });
			
		}
		
	}
}
