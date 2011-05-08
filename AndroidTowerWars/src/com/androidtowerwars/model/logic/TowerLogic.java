package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.World;

public class TowerLogic {
	
	public static synchronized void updateTower(ITower tower) {
		World.Team team = tower.getTeam();
		Rectangle range = tower.getRange();
		for(int n=0;n<GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).size();n++) {
			if (range.collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n)))) {
				fire(tower, GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n));
				break;
			}
		}
	}

	private static void fire(ITower tower, ISoldier soldier) {
		ProjectileController.createSprite(soldier, tower);
	}
}
