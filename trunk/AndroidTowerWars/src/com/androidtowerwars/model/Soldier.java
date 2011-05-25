package com.androidtowerwars.model;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.SoldierView;

public class Soldier extends Observable implements ISoldier, IObservableSprite {
	
	private Team team;
	protected float speed = 140; // pixels per second
	protected Rectangle range;

	protected int maxHealth = 100;
	protected int health = 100;

	protected int damage = 33;
	protected int value = 5;
	
	protected int cost = 5;

	private float x;
	private float y;

	public Soldier(float pX, float pY, float range, Team team) {
		x = pX;
		y = pY;
		this.team = team;
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-200, range, 400f);
		if (team == Team.GOOD) {
			speed = -speed;
		}
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public int getCost() {
		return cost;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setHealth(int health) {
	/*    if (health < 0) {
	        setDamage(0);
	        SoldierController.removeSoldier(this, GameActivity.instance.soldierController.soldierListMap.get(team));
	    } else {*/
	        this.health = health;
	    //}
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		setChanged();
		notifyObservers();
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Team getTeam() {
		return team;
	}
	
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value=value;
	}
	
	public Rectangle getRange(){
		return range;
	}
}
