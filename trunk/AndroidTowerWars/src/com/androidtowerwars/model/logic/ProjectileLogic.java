package com.androidtowerwars.model.logic;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.IProjectile;

public class ProjectileLogic {

	public static void updateProjectilePosition(IProjectile projectile, final float pSecondsElapsed) {
		projectile.setPosition(projectile.getX() + projectile.getDirection().getX() * projectile.getSpeed() * pSecondsElapsed,
		                       projectile.getY() + projectile.getDirection().getY() * projectile.getSpeed() * pSecondsElapsed);
	}
	public static void updateProjectileState(IProjectile projectile, final float pSecondsElapsed) {
		
			projectile.getTarget().setHealth(projectile.getTarget().getHealth() - projectile.getParent().getDamage());
			if (projectile.getTarget().getHealth() <= 0) {
				SoldierController.removeSoldier(projectile.getTarget(),GameActivity.instance.soldierController.soldierListMap.get(projectile.getTarget().getTeam()));
				projectile.getParent().increaseKills();
			}
			ProjectileController.removeProjectile(projectile, GameActivity.instance.projectileController.projectileListMap.get(projectile.getParent().getTeam()));
		
	}
}
