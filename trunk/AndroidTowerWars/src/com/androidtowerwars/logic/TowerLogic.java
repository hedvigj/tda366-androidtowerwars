package com.androidtowerwars.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.World;

public class TowerLogic {
	
	public static synchronized void updateTower(ITower tower, float pSecondsElapsed) {
		World.Team team = tower.getTeam();
		Rectangle range = tower.getRange();
		for(int n=0;n<GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).size();n++) {
			if (range.collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n)))) {
				SoldierLogic.killSoldier(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n));
			}
		}
	}
}
