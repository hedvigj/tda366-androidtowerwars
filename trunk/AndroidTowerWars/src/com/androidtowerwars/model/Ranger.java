package com.androidtowerwars.model;

import com.androidtowerwars.model.World.Team;

public class Ranger extends Soldier {
	
	private float speed = 70;
	private float range = 15;

	private int maxHealth = 200;
	private int health = 200;

	private int damage = 50;
	private int value = 10;

	public Ranger(float pX, float pY, float range, Team team) {
		super(pX, pY, range, team);
	}

}
