package com.androidtowerwars.model.logic;

import com.androidtowerwars.controller.PlayerController;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.Projectile;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;

public class ProjectileLogic {

	public static void updateProjectilePosition(IProjectile projectile, final float pSecondsElapsed) {
		projectile.setPosition(projectile.getX() + projectile.getDirection().getX() * projectile.getSpeed() * pSecondsElapsed,
		                       projectile.getY() + projectile.getDirection().getY() * projectile.getSpeed() * pSecondsElapsed);
	}
	public static void updateProjectileState(IProjectile projectile, final float pSecondsElapsed) {
		
			projectile.getTarget().setHealth(projectile.getTarget().getHealth() - projectile.getParent().getDamage());
			if (projectile.getTarget().getHealth() <= 0) {
				SoldierController.removeSoldier(projectile.getTarget(),Soldier.soldierListMap.get(projectile.getTarget().getTeam()));
				projectile.getParent().increaseKills();
				PlayerController.playerMap.get(projectile.getParent().getTeam()).increaseGold((int) (projectile.getTarget().getCost()*1.5));
			}
			ProjectileController.removeProjectile(projectile, Projectile.projectileListMap.get(projectile.getParent().getTeam()));
		
	}
}
