package com.androidtowerwars.model;



import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.primitive.Rectangle;

public class Tower implements ITower {

	public Rectangle range;
	private Team team;
	private float x;
	private float y;
	public int damage = 55;
	public float attack_speed = 0.7f; //seconds delay
	public int kills = 0;
	public static int cost = 20;
	
	private List<IProjectile> projectileList = new CopyOnWriteArrayList<IProjectile>();
	
	public Tower(float pX, float pY, float range, Team team) {
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-400, range, 900f); //not centered by width!
		this.team = team;
		this.x = pX;
		this.y = pY;
	}
	
	
	public float getAttackSpeed(){
		return this.attack_speed;
	}

	public List<IProjectile> getProjectiles() {
		return projectileList;
	}
	
	public void addProjectile(IProjectile projectile) {
		projectileList.add(projectile);
	}
	
	public boolean hasProjectiles() {
		if (projectileList.size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public Team getTeam() {
		return team;
	}
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}

	public int sellTower() {
		// TODO Auto-generated method stub
		return (int) (cost*0.8f);
	}
	
	public int getCost() {
		return cost;
	}

	public void setDamage(int damage) {
		this.damage = damage;		
	}

	public void upgradeTower() {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getRange() {
		return range;
	}
	
	public void increaseKills() {
		kills =+ 1;
	}
	
	public int getKills() {
		return kills;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
}
