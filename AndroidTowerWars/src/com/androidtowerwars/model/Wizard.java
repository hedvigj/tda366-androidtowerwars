package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public class Wizard extends Soldier{

	public Wizard(float pX, float pY,float range, Team team) {
		super(pX, pY, range, team);
		super.range = new Rectangle(pX-(10*0.5f)*0.5f, pY-200, 10, 400f);
		super.speed = 50;
		super.health = 300;
		super.maxHealth = 300;
		super.damage = 80;
		super.value = 20;
	}
}
