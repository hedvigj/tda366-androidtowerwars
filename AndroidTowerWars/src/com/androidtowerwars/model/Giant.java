package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public class Giant extends Soldier {
    public Giant(float pX, float pY, float range, Team team) {
        super(pX, pY, range, team);
        super.range     = new Rectangle(pX-(20*0.5f)*0.5f, pY-200, 20, 400f);
        super.speed     = 50;
        super.health    = 1000;
        super.maxHealth = 1000;
        super.damage    = 150;
        super.value     = 100;
    }
}