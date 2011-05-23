package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.model.World.Team;

public class Ranger extends Soldier {

	public Ranger(float pX, float pY, float range, Team team) {
		super(pX, pY, range, team);
		super.range = new Rectangle(pX-(15*0.5f)*0.5f, pY-200, 15, 400f);
		super.speed = 70;
		super.health = 200;
		super.maxHealth = 200;
		super.damage = 50;
		super.value = 10;
		if (team == World.Team.GOOD) {
			speed = -speed;
		}
	}
}
