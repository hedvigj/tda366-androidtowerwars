package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class Barrack implements IBarrack{
	
	private List<ISoldier> soldierList = new ArrayList<ISoldier>();
	
	public List<ISoldier> getSoldiers() {
		return soldierList;
	}

	public void addSoldiers(ISoldier soldier) {
		soldierList.add(soldier);
	}
	
	public void putSoldierList(List<ISoldier> soldier){
		soldierList = soldier;
	}
	
	public static List<ISoldier> getSoldierList(){
		List<ISoldier> mSoldierList = new ArrayList<ISoldier>(World.getInstance().getPlayer(Team.GOOD).getBarrack().getSoldiers());
		mSoldierList.addAll(World.getInstance().getPlayer(Team.EVIL).getBarrack().getSoldiers());
		return mSoldierList;
	}

	public List<ISoldier> getSoldiers(Team team2) {
		return World.getInstance().getPlayer(team2).getBarrack().getSoldiers();
	}

	private Team team;

	public Team getTeam() {
		return team;
	}

	


}
