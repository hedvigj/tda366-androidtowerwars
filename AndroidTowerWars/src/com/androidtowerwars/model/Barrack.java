package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class Barrack implements IBarrack{
	

	public static ConcurrentHashMap<Team, List<ISoldier>> soldierListMap = new ConcurrentHashMap<Team, List<ISoldier>>();
	private List<ISoldier> soldierList = new ArrayList<ISoldier>();
	
	public List<ISoldier> getSoldiers() {
		return soldierList;
	}

	public void addSoldiers(ISoldier soldier) {
		soldierList.add(soldier);
	}

	private Team team;

	public Team getTeam() {
		return team;
	}

}
