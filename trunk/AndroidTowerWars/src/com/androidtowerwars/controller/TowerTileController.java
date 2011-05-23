package com.androidtowerwars.controller;

import java.util.concurrent.CopyOnWriteArrayList;

import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;

public class TowerTileController {
	
	public TowerTileController() {
		World.towerTileListMap.put(Team.GOOD, new CopyOnWriteArrayList<TowerTile>());
		World.towerTileListMap.put(Team.EVIL, new CopyOnWriteArrayList<TowerTile>());
	}	
}
