package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;

import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.RangerView;
import com.androidtowerwars.view.SoldierView;

public class SoldierLogic {
    public static boolean rVariable = false;
    public static boolean aVariable = false;
    public static ISoldier mReciever;
    public static ISoldier mAttacker;

    public static void updateSoldier(ISoldier soldier, float pSecondsElapsed) {
        Team team = soldier.getTeam();   
        Sprite range = SoldierView.soldierSpriteMap.get(soldier);
        soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
        for (int n=0;n<World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().size();n++) {
            if (range.collidesWith(SoldierView.soldierSpriteMap.get(World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n)))) {
                ISoldier S = soldier;
                ISoldier R =  World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n);
                attackSoldier(S,R);
                break;
            }
        }
    }

    public static void updateRanger(ISoldier ranger, float pSecondsElapsed) {
        Team team            = ranger.getTeam();   
        AnimatedSprite range = RangerView.rangerSpriteMap.get(ranger);
        ranger.setPosition(ranger.getX() + ranger.getSpeed() * pSecondsElapsed, ranger.getY());
        for (int n=0;n<World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().size();n++) {
            if (range.collidesWith(RangerView.rangerSpriteMap.get(World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n))) ||
                    range.collidesWith(SoldierView.soldierSpriteMap.get(World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n)))) {
                ISoldier S = ranger;
                ISoldier R =  World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n);
                attackSoldier(S,R);
                break;
            }
        }
    }

    public static synchronized void attackSoldier(ISoldier attacker, ISoldier reciever) {
        mAttacker     = attacker;
        mReciever     = reciever;
        boolean alive = true;
        while (alive) {
            reciever.setHealth(reciever.getHealth() - attacker.getDamage());
            if (reciever.getHealth() <= 0) {
                rVariable = true;
                alive = false;
            } else {
                attacker.setHealth(attacker.getHealth() - reciever.getDamage());
                if (attacker.getHealth() <= 0) {
                    aVariable = true;
                    alive = false;
                }
            }
        }
    }

    public static ISoldier getReciver() {
        return mReciever;
    }

    public static ISoldier getAttacker() {
        return mAttacker;
    }

    public static boolean getrVariable() {
        return rVariable;
    }
    public static boolean getaVariable() {
        return aVariable;
    }
}