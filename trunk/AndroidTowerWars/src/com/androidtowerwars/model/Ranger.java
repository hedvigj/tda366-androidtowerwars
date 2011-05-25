package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public class Ranger extends Soldier {
    public Ranger(float pX, float pY, float range, Team team) {
        super(pX, pY, range, team);
        super.range = new Rectangle(pX-(15*0.5f)*0.5f, pY-200, 15, 400f);
        super.speed = 70;
        super.health = 200;
        super.maxHealth = 200;
        super.damage = 50;
        super.value = 10;
        super.cost = 7;
        if (team == Team.GOOD) {
            speed = -speed;
        }
    }
}