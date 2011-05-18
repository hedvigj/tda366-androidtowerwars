package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.PlayerController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;

public class SoldierLogic {
	
    public static void updateSoldier(ISoldier soldier, float pSecondsElapsed) {
        World.Team team = soldier.getTeam();
        Sprite range = Soldier.soldierSpriteMap.get(soldier);//soldier.getRange();
        soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
        for(int n=0;n<Soldier.soldierListMap.get(team.opposite()).size();n++) {
            if (range.collidesWith(Soldier.soldierSpriteMap.get(Soldier.soldierListMap.get(team.opposite()).get(n)))) {
                //SoldierController.removeSoldier(soldier, GameActivity.instance.soldierController.soldierListMap.get(team));
                ISoldier S = soldier;
                ISoldier R =  Soldier.soldierListMap.get(team.opposite()).get(n);
                //attackSoldier(); Should attack before kill?
                attackSoldier(S,R,team);
                break;
            }
        }
    }
    
    public static void attackSoldier(ISoldier attacker, ISoldier reciever, Team team) {
        //TODO: 
        reciever.setHealth(reciever.getHealth() - attacker.getDamage());
        //Log.d("TowerWars1", reciever.getHealth()+"");
        //Log.d("TowerWars2", attacker.getHealth()+"");
        if(reciever.getHealth() > 0) {
            attacker.setHealth(attacker.getHealth() - reciever.getDamage());
            Log.d("TowerWars3", reciever.getHealth()+"");
            Log.d("TowerWars4", attacker.getHealth()+"");
        } else {
            SoldierController.removeSoldier(reciever, Soldier.soldierListMap.get(team.opposite()));
            Log.d("TowerWars3", "remove");
        }
        
        if (attacker.getHealth() < 0) {
            SoldierController.removeSoldier(reciever, Soldier.soldierListMap.get(team));
            Log.d("TowerWars4", "remove");
        }
        
        
        
        
       /* if(reciever.getHealth() < 0) {
            //SoldierController.removeSoldier(reciever, GameActivity.instance.soldierController.soldierListMap.get(team.opposite()));
            Log.d("TowerWars1", reciever.getHealth()+"");
            Log.d("TowerWars2", attacker.getHealth()+"");
<<<<<<< .mine
            PlayerController.playerMap.get(attacker.getTeam()).increaseGold((int) (reciever.getCost()*1.5));
=======
        } else if(attacker.getHealth() < 0) {
            //SoldierController.removeSoldier(reciever, GameActivity.instance.soldierController.soldierListMap.get(team));
            Log.d("TowerWars3", reciever.getHealth()+"");
            Log.d("TowerWars4", attacker.getHealth()+"");
>>>>>>> .r81
        } else {
            attacker.setHealth(attacker.getHealth() - reciever.getDamage());
            Log.d("TowerWars5", reciever.getHealth()+"");
            Log.d("TowerWars6", attacker.getHealth()+"");
            if(attacker.getHealth() < 0) {
<<<<<<< .mine
                SoldierController.removeSoldier(attacker, GameActivity.instance.soldierController.soldierListMap.get(team));
                PlayerController.playerMap.get(reciever.getTeam()).increaseGold((int) (attacker.getCost()*1.5));
=======
                //SoldierController.removeSoldier(reciever, GameActivity.instance.soldierController.soldierListMap.get(team));
>>>>>>> .r81
            }
        }*/
    }
}
