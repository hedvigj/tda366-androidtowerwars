package com.androidtowerwars.model.logic;

import com.androidtowerwars.model.IBarrack;
import com.androidtowerwars.model.TestSoldier;
import com.androidtowerwars.model.World;

public class BarrackLogic {

	public static void updateBarrack(IBarrack barrack, float pSecondsElapsed){
		World.Team team = barrack.getTeam();
	}
	
	public TestSoldier createSoldier(){
		return new TestSoldier(0,0, null);
	}
}
