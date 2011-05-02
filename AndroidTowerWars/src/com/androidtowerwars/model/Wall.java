package com.androidtowerwars.model;

import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.ObserverSprite;

public class Wall implements IWall {
	private int maxHealth = 10000;
	private int health = 10000;
	private World.Team team;


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
}
