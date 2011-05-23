package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public class TarTower extends Tower {

	public TarTower(float pX, float pY, float range, Team team) {
		super(pX, pY, range, team);
		super.damage = 100;
		super.attack_speed = 0.5f;
		super.cost = 50;
		super.range =new Rectangle(pX-(range*0.5f)*0.5f, pY-400, range, 900f);
	}

}
