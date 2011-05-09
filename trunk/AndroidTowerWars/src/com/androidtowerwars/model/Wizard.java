package com.androidtowerwars.model;

import com.androidtowerwars.model.World.Team;

public class Wizard extends Soldier{
	
	private float speed = 50;
	private float range = 10;

	private int maxHealth = 300;
	private int health = 300;

	private int damage = 80;
	private int value = 20;

	public Wizard(float pX, float pY,float range, Team team) {
		super(pX, pY, range, team);
	}

}
