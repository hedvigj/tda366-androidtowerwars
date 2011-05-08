package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public interface ITower {
	public World.Team getTeam();

	public void setDamage();

	public int getDamage();
	
	public Rectangle getRange();
	
	public int sellTower();

	public void upgradeTower();
	// void shootProjectile();
}
