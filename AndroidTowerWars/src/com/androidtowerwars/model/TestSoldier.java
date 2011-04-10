package com.androidtowerwars.model;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.androidtowerwars.GameActivity;

public class TestSoldier implements ISoldier {

	private World.Team team;
	private float speed = 60; // pixels per second

	private int maxHealth = 100;
	private int health = 100;

	private int damage = 33;

	private float x;
	private float y;

	public TestSoldier(float pX, float pY, World.Team team) {
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
	
}
