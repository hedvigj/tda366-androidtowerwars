package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public class Wall implements IWall {
	private int maxHealth = 200;
	private int health = maxHealth;
	private Team team;
	private Rectangle range;
	
	public Wall(float pX, float pY, float range, Team team) {
		//this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-200, range, 400f); // TODO: �ndra range
		this.range = new Rectangle(pX-range, pY, range*2, 800f);
		this.team = team;
	}


	public Wall(Team team) {
		this.team = team;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	public Team getTeam(){
		return team;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setTeam(Team team){
		this.team=team;
	}
	
	public Rectangle getRange(){
		return range;
	}
}
