package com.androidtowerwars.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.view.ClickButton;
import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.WorldView;


public class World{
	

	public static ConcurrentHashMap<World.Team, List<ISoldier>> soldierListMap = new ConcurrentHashMap<World.Team, List<ISoldier>>();
	public static ConcurrentHashMap<World.Team, List<TowerTile>> towerTileListMap = new ConcurrentHashMap<World.Team, List<TowerTile>>();
	public static ConcurrentHashMap<World.Team, List<IProjectile>> projectileListMap = new ConcurrentHashMap<World.Team, List<IProjectile>>();
	
	
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

