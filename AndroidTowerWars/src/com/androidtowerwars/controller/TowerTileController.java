package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.ClickButton;

public class TowerTileController {
	
	public static ConcurrentHashMap<World.Team, List<TowerTile>> towerTileListMap = new ConcurrentHashMap<World.Team, List<TowerTile>>();
	public static ConcurrentHashMap<ClickButton, TowerTile> towerTileSpriteMap = new ConcurrentHashMap<ClickButton, TowerTile>();
	
	public TowerTileController() {
		towerTileListMap.put(Team.GOOD, new CopyOnWriteArrayList<TowerTile>());
		towerTileListMap.put(Team.EVIL, new CopyOnWriteArrayList<TowerTile>());
	}	
}
