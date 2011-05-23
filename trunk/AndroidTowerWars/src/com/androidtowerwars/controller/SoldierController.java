package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

import com.androidtowerwars.model.Barrack;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Ranger;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.ProjectileLogic;
import com.androidtowerwars.model.logic.SoldierLogic;
import com.androidtowerwars.view.RangerView;
import com.androidtowerwars.view.SoldierView;
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
	
	//===========================================================
	// Methods
	// ===========================================================
	public SoldierController() {
		Barrack.soldierListMap.put(Team.GOOD, new CopyOnWriteArrayList<ISoldier>());
		Barrack.soldierListMap.put(Team.EVIL, new CopyOnWriteArrayList<ISoldier>());
	}
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		for(List<ISoldier> soldierList: Barrack.soldierListMap.values()) {
			for(ISoldier soldier: soldierList) {
				
				if (soldier.getClass().equals(Soldier.class)) {
					SoldierLogic.updateSoldier(soldier, pSecondsElapsed);
				}
				if (soldier.getClass().equals(Ranger.class)) {
					SoldierLogic.updateRanger(soldier, pSecondsElapsed);
				}
				
				
				if(SoldierLogic.getrVariable()){
					removeSoldier(SoldierLogic.getReciver(), Barrack.soldierListMap.get(SoldierLogic.getReciver().getTeam()));
				}
				if(SoldierLogic.getaVariable()){
					removeSoldier(SoldierLogic.getAttacker(), Barrack.soldierListMap.get(SoldierLogic.getAttacker().getTeam()));
				}
				if(ProjectileLogic.getCheck()){
					removeSoldier(ProjectileLogic.mProjectile.getTarget(),Barrack.soldierListMap.get(ProjectileLogic.mProjectile.getTarget().getTeam()));
					ProjectileLogic.resetCheck();
				}
			}
		}
	}
	
	public static synchronized void removeSoldier(final ISoldier soldier, List<ISoldier> list) {
	   // Log.d("RemoveSoldier", list.get(list.lastIndexOf(soldier))+"");
	    list.remove(soldier);
	    //GameActivity.instance.
		WorldView.getInstance().runOnUpdateThread(new Runnable() {
			public void run() {
				/* Now it is save to remove the entity! */
				WorldView.getInstance().getScene().getLastChild()
						.detachChild(SoldierView.soldierSpriteMap.get(soldier));
				WorldView.getInstance().getScene().getLastChild()
				.detachChild(RangerView.rangerSpriteMap.get(soldier));
			}
		});
	}
	
	public static void createSprite(float pX, float pY, Team team) {
		Soldier soldier = new Soldier(pX, pY, 5, team); // TODO, Inte s�ker p� att 5 �r r�tt.
		SoldierView soldierSprite = new SoldierView(pX, pY, soldier);
		Barrack.soldierListMap.get(team).add(soldier);
		SoldierView.soldierSpriteMap.put(soldier, soldierSprite);
		soldier.addObserver(soldierSprite);
		WorldView.getInstance().getScene().getLastChild().attachChild(soldierSprite);
		Player.playerMap.get(team).decreaseGold(soldier.getCost());
	}
	
	public static void createAnimatedSprite(float pX, float pY,Team team){
		Ranger ranger = new Ranger(pX, pY, 7, team);
		RangerView rangerSprite = new RangerView(pX, pY, ranger);
		final AnimatedSprite cyclop = new AnimatedSprite(100, 50, RangerView.mCyclopTextureRegion);
        cyclop.animate(100);
        Barrack.soldierListMap.get(team).add(ranger);
        RangerView.rangerSpriteMap.put(ranger, rangerSprite);
        ranger.addObserver(rangerSprite);
        WorldView.getInstance().getScene().getLastChild().attachChild(cyclop);
        WorldView.getInstance().getScene().getLastChild().attachChild(rangerSprite);
        Player.playerMap.get(team).decreaseGold(ranger.getCost());

	}
	/**
	 * Creates a Timer Handler used to Spawn Sprites
	 */
//	public void createSpriteSpawnTimeHandler() {
//		soldierListMap.put(Team.EVIL, new CopyOnWriteArrayList<ISoldier>());
//		GameActivity.instance.getEngine().registerUpdateHandler(
//				spriteTimerHandler = new TimerHandler(1.4f,
//						new ITimerCallback() {
//							public void onTimePassed(
//									final TimerHandler pTimerHandler) {
//								spriteTimerHandler.reset();
//								final float xPos = 300;
//								final float yPos = WorldView.MAP_HEIGHT*0.52f;
//								createSprite(xPos, yPos, World.Team.EVIL);
//							}
//						}));
//	}
//	public void createRightSpawnTimeHandler() {
//		soldierListMap.put(Team.GOOD, new CopyOnWriteArrayList<ISoldier>());
//		GameActivity.instance.getEngine().registerUpdateHandler(
//				rSpriteTimerHandler = new TimerHandler(1.4f,
//						new ITimerCallback() {
//							public void onTimePassed(
//									final TimerHandler pTimerHandler) {
//								rSpriteTimerHandler.reset();
//								final float xPos = WorldView.MAP_WIDTH-300;
//								final float yPos = WorldView.MAP_HEIGHT*0.52f;
//								createSprite(xPos, yPos, World.Team.GOOD);
//							}
//						}));
//	}
}


// ===========================================================
// Inner and Anonymous Classes
// ===========================================================
