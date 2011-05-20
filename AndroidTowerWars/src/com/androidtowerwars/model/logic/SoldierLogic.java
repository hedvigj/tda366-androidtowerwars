package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.sprite.Sprite;

import android.util.Log;

import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.SoldierView;

public class SoldierLogic {
	
	public static boolean rVariable = false;
	public static boolean aVariable = false;
	public static ISoldier mReciever;
	public static ISoldier mAttacker;
	
    public static void updateSoldier(ISoldier soldier, float pSecondsElapsed) {
        World.Team team = soldier.getTeam();   
        Sprite range = SoldierView.soldierSpriteMap.get(soldier);//soldier.getRange();
        soldier.setPosition(soldier.getX() + soldier.getSpeed() * pSecondsElapsed, soldier.getY());
        for(int n=0;n<World.soldierListMap.get(team.opposite()).size();n++) {
            if (range.collidesWith(SoldierView.soldierSpriteMap.get(World.soldierListMap.get(team.opposite()).get(n)))) {
                //SoldierController.removeSoldier(soldier, GameActivity.instance.soldierController.soldierListMap.get(team));
                ISoldier S = soldier;
                ISoldier R =  World.soldierListMap.get(team.opposite()).get(n);
                //attackSoldier(); Should attack before kill?
                attackSoldier(S,R);
                break;
            }
        }
    }
   
    
    public static void attackSoldier(ISoldier attacker, ISoldier reciever) {
        //TODO: 
    	mAttacker = attacker;
    	mReciever = reciever;
        boolean alive = true;
        while(alive) {
            
            reciever.setHealth(reciever.getHealth() - attacker.getDamage());
            
            if(reciever.getHealth() <= 0) {
                rVariable = true;
                Log.d("TowerWars3", "removedefender"+"-"+reciever.getTeam());
                //Log.d("TowerWars1", reciever.getHealth());
                alive = false;
            } else {
                attacker.setHealth(attacker.getHealth() - reciever.getDamage());
                //Log.d("TowerWars3", reciever.getHealth()+"");
                //Log.d("TowerWars4", attacker.getHealth()+"");
            }
            
            if (attacker.getHealth() <= 0) {
            	aVariable = true;
                Log.d("TowerWars4", "removeattacker"+"-"+attacker.getTeam());
                alive = false;
            }
        }
        
        
        
     /*   reciever.setHealth(reciever.getHealth() - attacker.getDamage());
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
        
        */
        
        
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
    
    
    public static ISoldier getReciver(){
    	return mReciever;
    }
    
    public static ISoldier getAttacker(){
    	return mAttacker;
    }
    
    public static boolean setrVariable(){
    	return rVariable = false;
    }
    
    public static boolean setaVariable(){
    	return aVariable = false;
    }
    
    public static boolean getrVariable(){
    	return rVariable;
    }
    public static boolean getaVariable(){
    	return aVariable;
    }
    
}
