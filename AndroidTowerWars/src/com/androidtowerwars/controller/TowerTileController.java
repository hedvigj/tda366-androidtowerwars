package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.model.IButtonSprite;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;

public class TowerTileController {
	
	public static ConcurrentHashMap<World.Team, List<IButtonSprite>> towerTileListMap = new ConcurrentHashMap<World.Team, List<IButtonSprite>>();
	public static ConcurrentHashMap<Sprite, IButtonSprite> towerTileSpriteMap = new ConcurrentHashMap<Sprite, IButtonSprite>();
	
	public TowerTileController() {
		towerTileListMap.put(Team.GOOD, new CopyOnWriteArrayList<IButtonSprite>());
		towerTileListMap.put(Team.EVIL, new CopyOnWriteArrayList<IButtonSprite>());
	}	
	


}
