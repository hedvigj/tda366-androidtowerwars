package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.Entity;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Projectile;
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
		public static ConcurrentHashMap<World.Team, List<IProjectile>> projectileListMap = new ConcurrentHashMap<World.Team, List<IProjectile>>();
		public static ConcurrentHashMap<IProjectile, ObserverSprite> projectileSpriteMap = new ConcurrentHashMap<IProjectile, ObserverSprite>();
		
		//===========================================================
		// Methods
		// ===========================================================
		
		public ProjectileController() {
			projectileListMap.put(Team.EVIL, new CopyOnWriteArrayList<IProjectile>());
			projectileListMap.put(Team.GOOD, new CopyOnWriteArrayList<IProjectile>());
		}
		
		protected void onManagedUpdate(final float pSecondsElapsed) {
			super.onManagedUpdate(pSecondsElapsed);
			for(List<IProjectile> projectileList: projectileListMap.values()) {
				for(IProjectile projectile: projectileList) {
					ProjectileLogic.updateProjectilePosition(projectile, pSecondsElapsed);
					if (projectileSpriteMap.get(projectile).collidesWith(GameActivity.instance.soldierController.soldierSpriteMap.get(projectile.getTarget()))) {
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
							.detachChild(GameActivity.instance.projectileController.projectileSpriteMap.get(projectile));
				}
			});
		}
		
		public static void createSprite(ISoldier target, ITower parent) {
			World.Team team = parent.getTeam();
			Projectile projectile = new Projectile(target, parent);
			ObserverSprite projectileSprite = new ObserverSprite(projectile.getX(), projectile.getY(), ProjectileView.mArrowTextureRegion); //TODO make a projectile texture
			projectileListMap.get(team).add(projectile);
			projectileSpriteMap.put(projectile, projectileSprite);
			projectile.addObserver(projectileSprite);
			WorldView.getInstance().getScene().getLastChild().attachChild(projectileSprite);
		}
	
	
	
}