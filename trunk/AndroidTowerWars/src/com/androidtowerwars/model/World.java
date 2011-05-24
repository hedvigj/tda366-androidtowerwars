package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

	//public static Map<Team, Player> playerMap = new HashMap<Team, Player>();
	

	private static Player goodPlayer = new Player(Team.GOOD);
	private static Player evilPlayer = new Player(Team.EVIL);

	private static World instance = null;

	public static World getInstance() {
		if (instance == null) {
			instance = new World();
		}
		return instance;
	}

	public static Player getPlayer(Team team) {
		if (team == Team.EVIL) {
			return evilPlayer;
		} else
			return goodPlayer;
	}
}
