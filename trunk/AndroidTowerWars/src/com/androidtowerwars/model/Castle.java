package com.androidtowerwars.model;

import com.androidtowerwars.model.World.Team;

public class Castle {
	private int maxHealth = 10000;
	private int health = 10000;
	private World.Team team;
	private float x;
	private float y;

	public Castle(float pX, float pY, World.Team team) {
		this.team = team;
		x = pX;
		y = pY;
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
	public void setPosition(float x, float y){
		this.x=x;
		this.y=y;
	}
	public void setTeam(World.Team team){
		this.team=team;
	}

}
