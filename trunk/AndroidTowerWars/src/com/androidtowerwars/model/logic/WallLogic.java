package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.IWall;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;


public class WallLogic {

	public synchronized static void updateWall(Wall wall, float pSecondsElapsed){
		World.Team team = wall.getTeam();
		Rectangle range = wall.getRange();
		// TODO: Fixa detta, s� det fungerar riktigt.
		for(int n=0;n<GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).size();n++) {
		if (range.collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n)))){
			
			SoldierLogic.killSoldier(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n));
			damageCastle(wall, team);
			}
		}
	}
	
	public static synchronized void damageCastle(Wall wall, World.Team team){
		final int health = wall.getHealth();
		if(health <= 0){
			System.out.println("Muren �r f�rst�rd");
		}
		else
			wall.setHealth(health-10);
			System.out.println("Wall health:" + wall.getHealth());
	}
}
