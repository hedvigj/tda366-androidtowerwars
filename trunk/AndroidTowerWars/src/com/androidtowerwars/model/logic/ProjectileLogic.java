package com.androidtowerwars.model.logic;

import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.World;

public class ProjectileLogic {
	
	public static boolean check = false;
	public static IProjectile mProjectile;

	public static void updateProjectilePosition(IProjectile projectile, final float pSecondsElapsed) {
		projectile.setPosition(projectile.getX() + projectile.getDirection().getX() * projectile.getSpeed() * pSecondsElapsed,
		                       projectile.getY() + projectile.getDirection().getY() * projectile.getSpeed() * pSecondsElapsed);
	}
	public static void updateProjectileState(IProjectile projectile, final float pSecondsElapsed) {
			mProjectile = projectile;
			projectile.getTarget().setHealth(projectile.getTarget().getHealth() - projectile.getParent().getDamage());
			if (projectile.getTarget().getHealth() <= 0) {
				check = true;
				projectile.getParent().increaseKills();
				Player.playerMap.get(projectile.getParent().getTeam()).increaseGold((int) (projectile.getTarget().getCost()*1.5));
			}
	}
	
	public static boolean getCheck(){
		return check;
	}
	public static boolean resetCheck(){
		return check = false;
	}
}
