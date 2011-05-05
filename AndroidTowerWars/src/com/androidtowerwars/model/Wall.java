package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.WorldView;

public class Wall implements IWall {
	private int maxHealth = 10000;
	private int health = 20;
	private World.Team team;
	private static Rectangle range;
	
	public Wall(float pX, float pY, float range, World.Team team) {
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-200, range, 400f); // TODO: Ändra range
		this.team = team;
	}


	public Wall(World.Team team) {
		this.team = team;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	public World.Team getTeam(){
		return team;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setTeam(World.Team team){
		this.team=team;
	}
	
	public static Rectangle getRange(){
		return range;
	}
}
