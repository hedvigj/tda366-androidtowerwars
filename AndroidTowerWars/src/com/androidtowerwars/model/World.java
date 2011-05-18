package com.androidtowerwars.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.view.ClickButton;
import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.WorldView;


public class World{
	
	private ConcurrentHashMap<World.Team, List<ITower>> towerListMap = new ConcurrentHashMap<World.Team, List<ITower>>();
	private ConcurrentHashMap<ITower, Sprite> towerSpriteMap = new ConcurrentHashMap<ITower, Sprite>();
	private ConcurrentHashMap<World.Team, List<ISoldier>> soldierListMap = new ConcurrentHashMap<World.Team, List<ISoldier>>();
	private ConcurrentHashMap<ISoldier, ObserverSprite> soldierSpriteMap = new ConcurrentHashMap<ISoldier, ObserverSprite>();
	private ConcurrentHashMap<World.Team, List<IProjectile>> projectileListMap = new ConcurrentHashMap<World.Team, List<IProjectile>>();
	private ConcurrentHashMap<IProjectile, ObserverSprite> projectileSpriteMap = new ConcurrentHashMap<IProjectile, ObserverSprite>();
	private ConcurrentHashMap<World.Team, List<TowerTile>> towerTileListMap = new ConcurrentHashMap<World.Team, List<TowerTile>>();
	private ConcurrentHashMap<ClickButton, TowerTile> towerTileSpriteMap = new ConcurrentHashMap<ClickButton, TowerTile>();
	
	private static World instance = null;
	
	public World(){
		if(instance == null){
			instance = new World();
		}
	}
	
	public static World getInstance() {
		if (instance == null) {
			instance = new World(); 
		}
		return instance;
	}
	
    public static void setInstance(World instance) {
        World.instance = instance;
    }
	
	public ConcurrentHashMap<World.Team, List<ISoldier>> getSoldierListMap(){
		return soldierListMap;
	}
	
	public ConcurrentHashMap<ISoldier, ObserverSprite> getSoldierSpriteMap(){
		return soldierSpriteMap;
	}
	public ConcurrentHashMap<World.Team, List<ITower>> getTowerListMap(){
		return towerListMap;
	}
	
	public ConcurrentHashMap<ITower, Sprite> getTowerSpriteMap(){
		return towerSpriteMap;
	}
	
	public ConcurrentHashMap<World.Team, List<IProjectile>> getProjectileListMap(){
		return projectileListMap;
	}
	
	public ConcurrentHashMap<IProjectile, ObserverSprite> getProjectileSpriteMap(){
		return projectileSpriteMap;
	}
	
	public ConcurrentHashMap<World.Team, List<TowerTile>> getTowerTileListMap(){
		return towerTileListMap;
	}
	
	public ConcurrentHashMap<ClickButton, TowerTile> getTowerTileSpriteMap(){
		return towerTileSpriteMap;
	}

	
    public enum Team {
    	 GOOD, EVIL;
    	 
    	 public Team opposite() {
    		 if (this == Team.EVIL)
    			 return Team.GOOD;
    		 else
    			 return Team.EVIL;
    	 }
    	}
}    

