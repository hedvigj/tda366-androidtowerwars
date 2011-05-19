package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.ClickButton;

public class TowerTileController {
	
	public TowerTileController() {
		World.towerTileListMap.put(Team.GOOD, new CopyOnWriteArrayList<TowerTile>());
		World.towerTileListMap.put(Team.EVIL, new CopyOnWriteArrayList<TowerTile>());
	}	
}
