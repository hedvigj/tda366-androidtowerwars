package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class World {

	public static ConcurrentHashMap<Team, List<TowerTile>> towerTileListMap = new ConcurrentHashMap<Team, List<TowerTile>>();
	public static ConcurrentHashMap<Team, List<ITower>> towerListMap = new ConcurrentHashMap<Team, List<ITower>>();

	private List<ISoldier> goodSoldier = new ArrayList<ISoldier>();
	private List<ISoldier> evilSoldier = new ArrayList<ISoldier>();

	private static World instance = null;

	public static World getInstance() {
		if (instance == null) {
			instance = new World();
		}
		return instance;
	}

	public List<ISoldier> getSoldier(Team team) {
		if (team == Team.EVIL) {
			return evilSoldier;
		} else {
			return goodSoldier;
		}
	}

	public void addSoldier(ISoldier soldier, Team team) {
		if (team == Team.EVIL) {
			evilSoldier.add(soldier);
		} else {
			goodSoldier.add(soldier);
		}
	}
}
