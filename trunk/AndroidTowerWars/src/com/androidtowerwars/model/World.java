package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

	public static ConcurrentHashMap<Team, List<TowerTile>> towerTileListMap = new ConcurrentHashMap<Team, List<TowerTile>>();
	public static ConcurrentHashMap<Team, List<ITower>> towerListMap = new ConcurrentHashMap<Team, List<ITower>>();
	public static Map<Team, Player> playerMap = new HashMap<Team, Player>();
	

	private Player goodPlayer = new Player(Team.GOOD);
	private Player evilPlayer = new Player(Team.EVIL);

	private static World instance = null;

	public static World getInstance() {
		if (instance == null) {
			instance = new World();
		}
		return instance;
	}

	public Player getPlayer(Team team) {
		if (team == Team.EVIL) {
			return evilPlayer;
		} else
			return goodPlayer;
	}
}
