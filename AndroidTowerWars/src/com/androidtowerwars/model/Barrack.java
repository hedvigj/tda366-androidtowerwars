package com.androidtowerwars.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class Barrack implements IBarrack{
	

	public static ConcurrentHashMap<Team, List<ISoldier>> soldierListMap = new ConcurrentHashMap<Team, List<ISoldier>>();
	
	private Team team;

	public Team getTeam() {
		return team;
	}

}
