package com.androidtowerwars.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.model.logic.SoldierLogic;
import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.WorldView;


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
	public static ConcurrentHashMap<ISoldier, ObserverSprite> soldierSpriteMap = new ConcurrentHashMap<ISoldier, ObserverSprite>();
	
	//===========================================================
	// Methods
	// ===========================================================
	
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		for(List<ISoldier> soldierList: soldierListMap.values()) {
			for(ISoldier soldier: soldierList) {
				SoldierLogic.updateSoldier(soldier, pSecondsElapsed);
			}
		}
	}
	
	public static synchronized void removeSoldier(final ISoldier soldier, List<ISoldier> list) {
		list.remove(soldier);
		GameActivity.instance.runOnUpdateThread(new Runnable() {
			public void run() {
				/* Now it is save to remove the entity! */
				WorldView.getInstance().getScene().getLastChild()
						.detachChild(GameActivity.instance.soldierController.soldierSpriteMap.get(soldier));
			}
		});
	}
	
	private void createSprite(float pX, float pY, World.Team team) {
		Soldier soldier = new Soldier(pX, pY, 5, team); // TODO, Inte säker på att 5 är rätt.
		ObserverSprite soldierSprite = new ObserverSprite(pX, pY, GameActivity.instance.mSkeletonTextureRegion);
		soldierListMap.get(team).add(soldier);
		soldierSpriteMap.put(soldier, soldierSprite);
		soldier.addObserver(soldierSprite);
		WorldView.getInstance().getScene().getLastChild().attachChild(soldierSprite);
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
								final float xPos = 300;
								final float yPos = WorldView.MAP_HEIGHT*0.52f;
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
								final float xPos = WorldView.MAP_WIDTH-300;
								final float yPos = WorldView.MAP_HEIGHT*0.52f;
								createSprite(xPos, yPos, World.Team.GOOD);
							}
						}));
	}
}


// ===========================================================
// Inner and Anonymous Classes
// ===========================================================
