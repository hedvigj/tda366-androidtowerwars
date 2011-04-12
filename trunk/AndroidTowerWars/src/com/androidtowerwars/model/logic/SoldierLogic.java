package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.TestSoldier;

public class SoldierLogic {
	
	public static void updateSoldier(ISoldier soldier, float pSecondsElapsed) {
		soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
	}
	
	public static synchronized void killSoldier(final ISoldier soldier) {
		SoldierController.removeSoldier(soldier, GameActivity.instance.soldierController.soldierListMap.get(soldier.getTeam()));
	}
}
