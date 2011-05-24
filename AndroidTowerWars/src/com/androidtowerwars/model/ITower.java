package com.androidtowerwars.model;

import java.util.List;

import org.anddev.andengine.entity.primitive.Rectangle;

public interface ITower {
	public Team getTeam();

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

	public List<IProjectile> getProjectiles();
	
	public void addProjectile(IProjectile projectile);
	
	public boolean hasProjectiles();
	
}
