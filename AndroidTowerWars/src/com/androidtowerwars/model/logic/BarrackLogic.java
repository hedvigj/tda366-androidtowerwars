package com.androidtowerwars.model.logic;

import com.androidtowerwars.model.IBarrack;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.Team;

public class BarrackLogic {

	public static void updateBarrack(IBarrack barrack, float pSecondsElapsed){
		Team team = barrack.getTeam();
	}
	
	public Soldier createSoldier(){
		return new Soldier(0,0,0, null);
	}
}