package com.androidtowerwars.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.model.logic.TowerLogic;

public class TowerController {//extends Entity {
	
	public static ConcurrentHashMap<World.Team, List<ITower>> towerListMap = new ConcurrentHashMap<World.Team, List<ITower>>();
	public static ConcurrentHashMap<ITower, Sprite> towerSpriteMap = new ConcurrentHashMap<ITower, Sprite>();
	private static TowerController instance = null;
	
	private TowerController() {
		towerListMap.put(Team.GOOD, new CopyOnWriteArrayList<ITower>());
		towerListMap.put(Team.EVIL, new CopyOnWriteArrayList<ITower>());
		this.instance = this;
	}
	
	public static TowerController getInstance() {
		if (instance == null) {
			instance = new TowerController(); 
		}
		return instance;
	}
	
	
	public static synchronized Sprite createTestTower(float pX, float pY, TextureRegion pTextureRegion, float range, World.Team team) {
		Tower tower = new Tower(pX, pY, range, team);
		Sprite sprite = new Sprite(pX, pY, pTextureRegion);
		towerListMap.get(team).add(tower);
		towerSpriteMap.put(tower, sprite);
		
		return sprite;
	}
}
