package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.model.Barrack;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.SoldierView;

public class TowerLogic {
	
	public static synchronized void updateTower(ITower tower) {
		Team team = tower.getTeam();
		Rectangle range = tower.getRange();
		for(int n=0;n<Barrack.soldierListMap.get(team.opposite()).size();n++) {
			if (range.collidesWith(SoldierView.soldierSpriteMap.get(Barrack.soldierListMap.get(team.opposite()).get(n)))) {
				ProjectileController.createSprite(Barrack.soldierListMap.get(team.opposite()).get(n),tower);
				break;
			}
		}
	}
}