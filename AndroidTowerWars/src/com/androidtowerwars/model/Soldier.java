package com.androidtowerwars.model;

import java.util.Observable;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.util.Log;

public class Soldier extends Observable implements ISoldier, IObservableSprite {

	private World.Team team;
	protected float speed = 60; // pixels per second
	protected Rectangle range;

	protected int maxHealth = 100;
	protected int health = 100;

	protected int damage = 33;
	protected int value = 5;

	private float x;
	private float y;

	public Soldier(float pX, float pY, float range, World.Team team) {
		x = pX;
		y = pY;
		this.team = team;
		this.range = new Rectangle(pX-(range*0.5f)*0.5f, pY-200, range, 400f);
		if (team == World.Team.GOOD) {
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

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setHealth(int health) {
		this.health = health;

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

	public World.Team getTeam() {
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
