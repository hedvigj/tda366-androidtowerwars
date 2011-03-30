package com.androidtowerwars;

import java.util.*;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.constants.Constants;

public class Controller {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private TimerHandler spriteTimerHandler;
	public List<TestSoldier> soldierList = new LinkedList<TestSoldier>();
	
	//===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Create a Sprite at a specified location
	 * 
	 * @param pX
	 *            is the X Position of your Sprite
	 * @param pY
	 *            is the Y Position of your Sprite
	 */
	private void createSprite(float pX, float pY) {
		TestSoldier sprite = new TestSoldier(pX, pY, Main.instance.mSkeletonTextureRegion);
		soldierList.add(sprite);
		Main.instance.getEngine().getScene().getLastChild().attachChild(sprite);
	}

	/**
	 * Creates a Timer Handler used to Spawn Sprites
	 */
	public void createSpriteSpawnTimeHandler() {
		Main.instance.getEngine().registerUpdateHandler(
				spriteTimerHandler = new TimerHandler(1.0f,
						new ITimerCallback() {
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								spriteTimerHandler.reset();
								// Random Position Generator
								final float xPos = 200;
								final float yPos = Constants.MAP_HEIGHT / 2;
								createSprite(xPos, yPos);
							}
						}));
	}
}


// ===========================================================
// Inner and Anonymous Classes
// ===========================================================
