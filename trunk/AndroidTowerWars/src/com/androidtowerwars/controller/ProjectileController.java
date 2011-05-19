package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.Entity;

import android.util.Log;

import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Projectile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.model.logic.ProjectileLogic;
import com.androidtowerwars.view.ObserverSprite;
import com.androidtowerwars.view.ProjectileView;
import com.androidtowerwars.view.SoldierView;
import com.androidtowerwars.view.WorldView;

public class ProjectileController extends Entity {
		// ===========================================================
		// Fields
		// ===========================================================
		
		//===========================================================
		// Methods
		// ===========================================================
	
		public ProjectileController() {
			World.projectileListMap.put(Team.EVIL, new CopyOnWriteArrayList<IProjectile>());
			World.projectileListMap.put(Team.GOOD, new CopyOnWriteArrayList<IProjectile>());
		}
		
		protected void onManagedUpdate(final float pSecondsElapsed) {
			super.onManagedUpdate(pSecondsElapsed);
			for(List<IProjectile> projectileList: World.projectileListMap.values()) {
				for(IProjectile projectile: projectileList) {
					ProjectileLogic.updateProjectilePosition(projectile, pSecondsElapsed);
					if (ProjectileView.projectileSpriteMap.get(projectile).collidesWith(SoldierView.soldierSpriteMap.get(projectile.getTarget()))) {
						ProjectileLogic.updateProjectileState(projectile, pSecondsElapsed);
				}
				}
			}
		}
		
		public static synchronized void removeProjectile(final IProjectile projectile, List<IProjectile> list) {
			list.remove(projectile);
			//GameActivity.instance.
			WorldView.getInstance().runOnUpdateThread(new Runnable() {
				public void run() {
					/* Now it is save to remove the entity! */
					WorldView.getInstance().getScene().getLastChild()
							.detachChild(ProjectileView.projectileSpriteMap.get(projectile));
					Log.d("Tower","K÷÷÷÷R");
				}
			});
		}
		
		public static void createSprite(ISoldier target, ITower parent) {
			World.Team team = parent.getTeam();
			Projectile projectile = new Projectile(target, parent);
			ProjectileView projectileSprite = new ProjectileView(projectile.getX(), projectile.getY()); //TODO make a projectile texture
			World.projectileListMap.get(team).add(projectile);
			ProjectileView.projectileSpriteMap.put(projectile, projectileSprite);
			projectile.addObserver(projectileSprite);
			WorldView.getInstance().getScene().getLastChild().attachChild(projectileSprite);
		}
	
	
	
}
