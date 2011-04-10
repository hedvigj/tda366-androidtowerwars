package com.androidtowerwars.model;

public interface ISoldier {
	
	public World.Team getTeam();
	
	public void setHealth(int health);

	public int getHealth();

	public int getMaxHealth();

	public void setDamage(int damage);

	public int getDamage();

	public float getX();
	
	public float getY();

	public void setPosition(float x, float y);
	
	public float getSpeed();
	
	public void setSpeed(float speed);
}
