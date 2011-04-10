package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public interface ITower {
	public World.Team getTeam();

	public void setAttack();

	public int getAttack();
	
	public Rectangle getRange();
	
	public int sellTower();

	public void upgradeTower();
	// void shootProjectile();
}
