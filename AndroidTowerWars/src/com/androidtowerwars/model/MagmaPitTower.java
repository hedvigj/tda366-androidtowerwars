package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

public class MagmaPitTower extends Tower {
    public MagmaPitTower(float pX, float pY, float range, Team team) {
        super(pX, pY, range, team);
        super.damage       = 150;
        super.attack_speed = 0.8f;
        super.cost         = 50;
        super.range        = new Rectangle(pX-(range*0.5f)*0.5f, pY-400, range, 900f);
    }
}