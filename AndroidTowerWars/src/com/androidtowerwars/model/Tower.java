package com.androidtowerwars.model;



import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.model.logic.TowerLogic;
import com.androidtowerwars.view.WorldView;

public class Tower implements ITower {

	private Rectangle range;
	private World.Team team;
	private float x;
	private float y;
	private int damage = 55;
	private float attack_speed = 0.7f; //seconds delay
	private int kills = 0;
	public static int cost = 20;
	
	public Tower(float pX, float pY, float range, World.Team team) {
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-400, range, 900f); //not centered by width!
		this.team = team;
		this.x = pX;
		this.y = pY;
	}
	
	
	public float getAttackSpeed(){
		return this.attack_speed;
	}

	
	public World.Team getTeam() {
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
