package com.androidtowerwars.logic;

import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.TestSoldier;

public class SoldierLogic {
	
	public static void updateSoldier(ISoldier soldier, Sprite sprite, float pSecondsElapsed) {
		sprite.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
		soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
	}
	
	public static synchronized void killSoldier(final ISoldier soldier) {
		GameActivity.instance.runOnUpdateThread(new Runnable() {
			public void run() {
				/* Now it is save to remove the entity! */
				SoldierController.removeSoldier(soldier, GameActivity.instance.soldierController.soldierListMap.get(soldier.getTeam()));
				GameActivity.instance.getEngine().getScene().getLastChild()
						.detachChild(GameActivity.instance.soldierController.soldierSpriteMap.get(soldier));
			}
		});
	}
}
