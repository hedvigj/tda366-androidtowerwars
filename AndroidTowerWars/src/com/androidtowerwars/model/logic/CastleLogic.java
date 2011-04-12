package com.androidtowerwars.model.logic;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.Castle;
import com.androidtowerwars.model.World;


public class CastleLogic {

	public static void updateCastle(Castle castle, float pSecondsElapsed){
		World.Team team = castle.getTeam();
	}
	
	public static synchronized void damageCastle(Castle castle, World.Team team){
		castle.setHealth(castle.getHealth()-10);
	}
}
