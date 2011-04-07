package com.androidtowerwars;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.androidtowerwars.model.TestSoldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;


public class Controller {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private TimerHandler spriteTimerHandler;
	private TimerHandler rSpriteTimerHandler;
	public HashMap<World.Team, List<TestSoldier>> soldierListMap = new HashMap<World.Team, List<TestSoldier>>();
	
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
	private void createSprite(float pX, float pY, World.Team team) {
		TestSoldier sprite = new TestSoldier(pX, pY, GameActivity.instance.mSkeletonTextureRegion, team);
		soldierListMap.get(team).add(sprite);
		GameActivity.instance.getEngine().getScene().getLastChild().attachChild(sprite);
	}
	/**
	 * Creates a Timer Handler used to Spawn Sprites
	 */
	public void createSpriteSpawnTimeHandler() {
		soldierListMap.put(Team.EVIL, new LinkedList<TestSoldier>());
		GameActivity.instance.getEngine().registerUpdateHandler(
				spriteTimerHandler = new TimerHandler(1.0f,
						new ITimerCallback() {
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								spriteTimerHandler.reset();
								final float xPos = 200;
								final float yPos = World.MAP_HEIGHT / 2;
								createSprite(xPos, yPos, World.Team.EVIL);
							}
						}));
	}
	public void createRightSpawnTimeHandler() {
		soldierListMap.put(Team.GOOD, new LinkedList<TestSoldier>());
		GameActivity.instance.getEngine().registerUpdateHandler(
				rSpriteTimerHandler = new TimerHandler(2.0f,
						new ITimerCallback() {
							public void onTimePassed(
									final TimerHandler pTimerHandler) {
								rSpriteTimerHandler.reset();
								final float xPos = World.MAP_WIDTH-200;
								final float yPos = World.MAP_HEIGHT / 2;
								createSprite(xPos, yPos, World.Team.GOOD);
							}
						}));
	}
}


// ===========================================================
// Inner and Anonymous Classes
// ===========================================================
