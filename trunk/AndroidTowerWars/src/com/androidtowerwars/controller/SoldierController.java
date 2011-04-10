package com.androidtowerwars.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.logic.SoldierLogic;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.TestSoldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;


public class SoldierController extends Entity {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private TimerHandler spriteTimerHandler;
	private TimerHandler rSpriteTimerHandler;
	public static ConcurrentHashMap<World.Team, List<ISoldier>> soldierListMap = new ConcurrentHashMap<World.Team, List<ISoldier>>();
	public static ConcurrentHashMap<ISoldier, Sprite> soldierSpriteMap = new ConcurrentHashMap<ISoldier, Sprite>();
	
	//===========================================================
	// Methods
	// ===========================================================
	
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		for(List<ISoldier> soldierList: soldierListMap.values()) {
			for(ISoldier soldier: soldierList) {
				SoldierLogic.updateSoldier(soldier, soldierSpriteMap.get(soldier), pSecondsElapsed);
			}
		}
	}
	
	public static synchronized void removeSoldier(ISoldier soldier, List<ISoldier> list) {
		list.remove(soldier);
	}
	
	private void createSprite(float pX, float pY, World.Team team) {
		TestSoldier soldier = new TestSoldier(pX, pY, team);
		Sprite soldierSprite = new Sprite(pX, pY, GameActivity.instance.mSkeletonTextureRegion);
		soldierListMap.get(team).add(soldier);
		soldierSpriteMap.put(soldier, soldierSprite);
		GameActivity.instance.getEngine().getScene().getLastChild().attachChild(soldierSprite);
	}
	/**
	 * Creates a Timer Handler used to Spawn Sprites
	 */
	public void createSpriteSpawnTimeHandler() {
		soldierListMap.put(Team.EVIL, new CopyOnWriteArrayList<ISoldier>());
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
		soldierListMap.put(Team.GOOD, new CopyOnWriteArrayList<ISoldier>());
		GameActivity.instance.getEngine().registerUpdateHandler(
				rSpriteTimerHandler = new TimerHandler(1.0f,
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
