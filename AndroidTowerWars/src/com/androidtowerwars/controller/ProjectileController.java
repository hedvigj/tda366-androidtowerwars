package com.androidtowerwars.controller;

import java.util.List;

import org.anddev.andengine.entity.Entity;

import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Projectile;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.ProjectileLogic;
import com.androidtowerwars.view.ProjectileView;
import com.androidtowerwars.view.RangerView;
import com.androidtowerwars.view.SoldierView;
import com.androidtowerwars.view.WorldView;

public class ProjectileController extends Entity {		
    protected void onManagedUpdate(final float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        Player[] players = { World.getPlayer(Team.GOOD), World.getPlayer(Team.EVIL) };
        for (Player player : players) {
            for (TowerTile towerTile : player.getTowerTiles()) {
                if (towerTile.isOccupied()) {
                    ITower tower = towerTile.getTower();
                    if (tower.hasProjectiles()) {
                        for (IProjectile projectile : tower.getProjectiles()) {
                        	if (projectile.getTarget().getHealth() > 0) {
	                            ProjectileLogic.updateProjectilePosition(projectile, pSecondsElapsed);
	                            if (ProjectileView.projectileSpriteMap.get(projectile).collidesWith(SoldierView.soldierSpriteMap.get(projectile.getTarget()))) {
	                                ProjectileLogic.updateProjectileState(projectile, pSecondsElapsed);
	                                removeProjectile(projectile, tower.getProjectiles());
	                            } else if (ProjectileView.projectileSpriteMap.get(projectile).collidesWith(RangerView.rangerSpriteMap.get(projectile.getTarget()))) {
	                                ProjectileLogic.updateProjectileState(projectile, pSecondsElapsed);
	                                removeProjectile(projectile, tower.getProjectiles());
	                            }
                        	}
                        	else {
                        		removeProjectile(projectile, tower.getProjectiles());
                        	}
                        }
                    }
                }
            }
        }
    }

    public static synchronized void removeProjectile(final IProjectile projectile, List<IProjectile> list) {
        list.remove(projectile);
        WorldView.getInstance().runOnUpdateThread(new Runnable() {
            public void run() {
                /* Now it is save to remove the entity! */
                WorldView.getInstance().getScene().getLastChild()
                .detachChild(ProjectileView.projectileSpriteMap.get(projectile));
            }
        });
    }

    public static void createSprite(ISoldier target, ITower parent) {
        Team team                       = parent.getTeam();
        Projectile projectile           = new Projectile(target, parent);
        ProjectileView projectileSprite = new ProjectileView(projectile.getX(), projectile.getY());
        parent.addProjectile(projectile);
        ProjectileView.projectileSpriteMap.put(projectile, projectileSprite);
        projectile.addObserver(projectileSprite);
        WorldView.getInstance().getScene().getLastChild().attachChild(projectileSprite);
    }
}