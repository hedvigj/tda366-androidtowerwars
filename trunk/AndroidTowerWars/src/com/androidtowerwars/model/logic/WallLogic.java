package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;


public class WallLogic {

	public synchronized static void updateWall(Wall wall, float pSecondsElapsed){
		World.Team team = wall.getTeam();
		Rectangle range = wall.getRange();

		for(int n=0;n<Soldier.soldierListMap.get(team.opposite()).size();n++) {
		if (range.collidesWith(Soldier.soldierSpriteMap.get(Soldier.soldierListMap.get(team.opposite()).get(n)))){
			
			SoldierController.removeSoldier(Soldier.soldierListMap.get(team.opposite()).get(n),
					Soldier.soldierListMap.get(Soldier.soldierListMap.get(team.opposite()).get(n).getTeam()));
			damageCastle(wall, team);
			}
		}
	}
	
	public static synchronized void damageCastle(Wall wall, World.Team team){
		final int health = wall.getHealth();
		if(health <= 0){
			Log.d("TowerWars", "Muren �r f�rst�rd");
		}
		else
			wall.setHealth(health-10);
	}
}
