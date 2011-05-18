package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.Entity;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Projectile;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.model.logic.ProjectileLogic;
import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.ProjectileView;
import com.androidtowerwars.view.WorldView;

public class ProjectileController extends Entity {
		// ===========================================================
		// Fields
		// ===========================================================
		
		//===========================================================
		// Methods
		// ===========================================================
		
		public ProjectileController() {
			World.getInstance().getProjectileListMap().put(Team.EVIL, new CopyOnWriteArrayList<IProjectile>());
			World.getInstance().getProjectileListMap().put(Team.GOOD, new CopyOnWriteArrayList<IProjectile>());
		}
		
		protected void onManagedUpdate(final float pSecondsElapsed) {
			super.onManagedUpdate(pSecondsElapsed);
			for(List<IProjectile> projectileList: World.getInstance().getProjectileListMap().values()) {
				for(IProjectile projectile: projectileList) {
					ProjectileLogic.updateProjectilePosition(projectile, pSecondsElapsed);
					if (World.getInstance().getProjectileSpriteMap().get(projectile).collidesWith(World.getInstance().getSoldierSpriteMap().get(projectile.getTarget()))) {
						ProjectileLogic.updateProjectileState(projectile, pSecondsElapsed);
				}
				}
			}
		}
		
		public static synchronized void removeProjectile(final IProjectile projectile, List<IProjectile> list) {
			list.remove(projectile);
			GameActivity.instance.runOnUpdateThread(new Runnable() {
				public void run() {
					/* Now it is save to remove the entity! */
					WorldView.getInstance().getScene().getLastChild()
							.detachChild(World.getInstance().getProjectileSpriteMap().get(projectile));
				}
			});
		}
		
		public static void createSprite(ISoldier target, ITower parent) {
			World.Team team = parent.getTeam();
			Projectile projectile = new Projectile(target, parent);
			ObserverSprite projectileSprite = new ObserverSprite(projectile.getX(), projectile.getY(), ProjectileView.mArrowTextureRegion); //TODO make a projectile texture
			World.getInstance().getProjectileListMap().get(team).add(projectile);
			World.getInstance().getProjectileSpriteMap().put(projectile, projectileSprite);
			projectile.addObserver(projectileSprite);
			WorldView.getInstance().getScene().getLastChild().attachChild(projectileSprite);
		}
	
	
	
}
