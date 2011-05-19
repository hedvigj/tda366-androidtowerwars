package com.androidtowerwars.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.Entity;

import android.util.Log;

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
			Projectile.projectileListMap.put(Team.EVIL, new CopyOnWriteArrayList<IProjectile>());
			Projectile.projectileListMap.put(Team.GOOD, new CopyOnWriteArrayList<IProjectile>());
		}
		
		protected void onManagedUpdate(final float pSecondsElapsed) {
			super.onManagedUpdate(pSecondsElapsed);
			for(List<IProjectile> projectileList: Projectile.projectileListMap.values()) {
				for(IProjectile projectile: projectileList) {
					ProjectileLogic.updateProjectilePosition(projectile, pSecondsElapsed);
					if (Projectile.projectileSpriteMap.get(projectile).collidesWith(Soldier.soldierSpriteMap.get(projectile.getTarget()))) {
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
							.detachChild(Projectile.projectileSpriteMap.get(projectile));
					Log.d("Tower","K÷÷÷÷R");
				}
			});
		}
		
		public static void createSprite(ISoldier target, ITower parent) {
			World.Team team = parent.getTeam();
			Projectile projectile = new Projectile(target, parent);
			ObserverSprite projectileSprite = new ObserverSprite(projectile.getX(), projectile.getY(), ProjectileView.mArrowTextureRegion); //TODO make a projectile texture
			Projectile.projectileListMap.get(team).add(projectile);
			Projectile.projectileSpriteMap.put(projectile, projectileSprite);
			projectile.addObserver(projectileSprite);
			WorldView.getInstance().getScene().getLastChild().attachChild(projectileSprite);
		}
	
	
	
}
