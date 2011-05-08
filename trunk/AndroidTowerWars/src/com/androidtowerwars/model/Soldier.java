package com.androidtowerwars.model;

import java.util.Observable;

import android.util.Log;

public class Soldier extends Observable implements ISoldier {

	private World.Team team;
	private float speed = 60; // pixels per second

	private int maxHealth = 100;
	private int health = 100;

	private int damage = 33;
	private int value = 5;

	private float x;
	private float y;

	public Soldier(float pX, float pY, World.Team team) {
		x = pX;
		y = pY;
		this.team = team;
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
}
