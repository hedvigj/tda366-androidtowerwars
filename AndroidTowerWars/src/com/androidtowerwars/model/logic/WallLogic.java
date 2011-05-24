package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.controller.PlayerController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.SoldierView;
import com.androidtowerwars.view.TouchView;


public class WallLogic {

	public synchronized static void updateWall(Wall wall, float pSecondsElapsed){
		Team team = wall.getTeam();
		Rectangle range = wall.getRange();

		for(int n=0;n<World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().size();n++) {
		if (range.collidesWith(SoldierView.soldierSpriteMap.get(World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n)))){
			
			SoldierController.removeSoldier(World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n),
					World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers(World.getInstance().getPlayer(team.opposite()).getBarrack().getSoldiers().get(n).getTeam()));
			damageCastle(wall);
			}
		}
	}
	
	public static synchronized void damageCastle(Wall wall){
		final int health = wall.getHealth();
		if(health <= 0){
			if (wall.getTeam() == Team.EVIL )
				PlayerController.gameOver(true);
			else
				PlayerController.gameOver(false);
		}
		else
			wall.setHealth(health-10);
	}
}
