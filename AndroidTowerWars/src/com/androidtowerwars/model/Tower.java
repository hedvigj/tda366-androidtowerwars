package com.androidtowerwars.model;



import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Rectangle;

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
	
	private int last_fire_time = 0;
	private TimerHandler timerHandler;
	
	
	public Tower(float pX, float pY, float range, World.Team team) {
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-200, range, 400f); //not centered by width!
		this.team = team;
		this.x = pX;
		this.y = pY;
		WorldView.getInstance().registerUpdateHandler(
		timerHandler = new TimerHandler(attack_speed,
				new ITimerCallback() {
					public void onTimePassed(
							final TimerHandler pTimerHandler) {
						timerHandler.reset();
						TowerLogic.updateTower(Tower.this);
					}
				}));
		
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
		return 0;
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
