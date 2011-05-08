package com.androidtowerwars.model.logic;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.model.IProjectile;

public class ProjectileLogic {
	
	public static void updateProjectile(IProjectile projectile, final float pSecondsElapsed) {
		projectile.setPosition(projectile.getX() + projectile.getDirection().getX() * projectile.getSpeed() * pSecondsElapsed, projectile.getY() + projectile.getDirection().getY() * projectile.getSpeed() * pSecondsElapsed);
		if (GameActivity.instance.projectileController.projectileSpriteMap.get(projectile).collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(projectile.getTarget()))) {
			projectile.getTarget().setHealth(projectile.getTarget().getHealth() - projectile.getParent().getDamage());
			if (projectile.getTarget().getHealth() <= 0) {
				SoldierLogic.killSoldier(projectile.getTarget());
				projectile.getParent().increaseKills();
			}
			killProjectile(projectile);
		}
	}
	
	public static synchronized void killProjectile(final IProjectile projectile) {
		ProjectileController.removeProjectile(projectile, GameActivity.instance.projectileController.projectileListMap.get(projectile.getParent().getTeam()));
	}
}
