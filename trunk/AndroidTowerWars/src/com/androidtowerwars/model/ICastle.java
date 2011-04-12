package com.androidtowerwars.model;

public interface ICastle {
	
	public int getMaxHealth();
	public int getHealth();
	public void setHealth(int health); 
	public void setPosition(float x, float y);
	public void setTeam(World.Team team);
	public World.Team getTeam();
}
