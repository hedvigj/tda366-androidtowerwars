package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;

public class SoldierLogic {
	
    public static void updateSoldier(ISoldier soldier, float pSecondsElapsed) {
        World.Team team = soldier.getTeam();
        Sprite range = SoldierController.soldierSpriteMap.get(soldier);//soldier.getRange();
        soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
        for(int n=0;n<GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).size();n++) {
            if (range.collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(GameActivity.instance.soldierController.soldierListMap.get(team.opposite()).get(n)))) {
                SoldierController.removeSoldier(soldier, GameActivity.instance.soldierController.soldierListMap.get(team));
                //attackSoldier(); Should attack before kill?
                Log.d("TowerWars", "diee!");
                break;
            }
        }
    }
    
    public static void attackSoldier(Soldier attacker, Soldier reciever, Team team) {
        //TODO: 
        reciever.setHealth(reciever.getHealth() - attacker.getDamage());
        if(reciever.getHealth() < 0) {
            SoldierController.removeSoldier(reciever, GameActivity.instance.soldierController.soldierListMap.get(team));
        } else {
            attacker.setHealth(attacker.getHealth() - reciever.getDamage());
        }
    }
}
