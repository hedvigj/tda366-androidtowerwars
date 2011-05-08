package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public interface ITower {
	public World.Team getTeam();

	public float getX();
	
	public float getY();
	
	public void setDamage(int damage);

	public int getDamage();
	
	public Rectangle getRange();
	
	public int sellTower();

	public void upgradeTower();
	// void shootProjectile();
	
	public void increaseKills();
	
	public int getKills();
}
