package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;

public class SoldierLogic {
	
	public static void updateSoldier(ISoldier soldier, float pSecondsElapsed) {
		World.Team team = soldier.getTeam();
		Rectangle range = soldier.getRange();
		soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
		for(int n=0;n<GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).size();n++) {
			if (range.collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n)))) {
				//killSoldier(soldier);
				break;
			}
		}
	}
	
	public static synchronized void killSoldier(final ISoldier soldier) {
		SoldierController.removeSoldier(soldier, GameActivity.instance.soldierController.soldierListMap.get(soldier.getTeam()));
	}
}
