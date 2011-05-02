package com.androidtowerwars.model.logic;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;


public class WallLogic {

	public static void updateCastle(Wall castle, float pSecondsElapsed){
		World.Team team = castle.getTeam();
	}
	
	public static synchronized void damageCastle(Wall castle, World.Team team){
		castle.setHealth(castle.getHealth()-10);
	}
}
