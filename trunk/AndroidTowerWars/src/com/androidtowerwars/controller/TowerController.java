package com.androidtowerwars.controller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.MagmaPitTower;
import com.androidtowerwars.model.TarTower;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.TowerLogic;
import com.androidtowerwars.view.TowerView;
import com.androidtowerwars.view.WorldView;

public class TowerController {//extends Entity {
	
	private static TowerController instance = null;
	private static ConcurrentHashMap<ITower, TimerHandler> timerHandlerMap = new ConcurrentHashMap<ITower, TimerHandler>();
	
	public TowerController() {
		
		setInstance(this);
	}
	
	public static TowerController getInstance() {
		if (instance == null) {
			instance = new TowerController(); 
		}
		return instance;
	}

    public static synchronized Tower createTestTower(float pX, float pY, TextureRegion pTextureRegion, float range, Team team) {
        final Tower tower = new Tower(pX, pY, range, team);
        Sprite sprite = new Sprite(pX, pY, pTextureRegion);
       	TowerView.towerSpriteMap.put(tower, sprite);
        GameActivity.setTimestamp(0);
        WorldView.getInstance().getScene().getLastChild().attachChild(sprite);
        TimerHandler timerHandler = new TimerHandler(tower.getAttackSpeed(),
                        new ITimerCallback() {
                            public void onTimePassed(
                                    final TimerHandler pTimerHandler) {
                                pTimerHandler.reset();
                                TowerLogic.updateTower(tower);
                            }
                        });
        timerHandlerMap.put(tower, timerHandler);
        WorldView.getInstance().registerUpdateHandler(timerHandler);
        World.getPlayer(team).decreaseGold(tower.getCost());
        return tower;
    }
    
    public static synchronized Tower createMagmaPitTower(float pX, float pY, TextureRegion pTextureRegion, float range, Team team) {
        final MagmaPitTower tower = new MagmaPitTower(pX, pY, range, team);
        Sprite sprite = new Sprite(pX, pY, pTextureRegion);
        TowerView.towerSpriteMap.put(tower, sprite);
        GameActivity.setTimestamp(0);
        WorldView.getInstance().getScene().getLastChild().attachChild(sprite);
        TimerHandler timerHandler = new TimerHandler(tower.getAttackSpeed(),
                        new ITimerCallback() {
                            public void onTimePassed(
                                    final TimerHandler pTimerHandler) {
                                pTimerHandler.reset();
                                TowerLogic.updateTower(tower);
                            }
                        });
        timerHandlerMap.put(tower, timerHandler);
        WorldView.getInstance().registerUpdateHandler(timerHandler);
        World.getPlayer(team).decreaseGold(tower.getCost());
        return tower;
    }

    public static synchronized Tower createTarTower(float pX, float pY, TextureRegion pTextureRegion, float range, Team team) {
        final TarTower tower = new TarTower(pX, pY, range, team);
        Sprite sprite = new Sprite(pX, pY, pTextureRegion);
        TowerView.towerSpriteMap.put(tower, sprite);
        GameActivity.setTimestamp(0);
        WorldView.getInstance().getScene().getLastChild().attachChild(sprite);
        TimerHandler timerHandler = new TimerHandler(tower.getAttackSpeed(),
                        new ITimerCallback() {
                            public void onTimePassed(
                                    final TimerHandler pTimerHandler) {
                                pTimerHandler.reset();
                                TowerLogic.updateTower(tower);
                            }
                        });
        timerHandlerMap.put(tower, timerHandler);
        WorldView.getInstance().registerUpdateHandler(timerHandler);
        World.getPlayer(team).decreaseGold(tower.getCost());
        return tower;
    }
    public static synchronized void sellTower(final TowerTile towerTile) {
        int i = (int) ((int) towerTile.getTower().getCost()*0.75);
        World.getPlayer(towerTile.getTeam()).increaseGold(i);
        removeTower(towerTile);
        towerTile.resetOccupied();
    }
    
    public static synchronized void removeTower(final TowerTile towerTile) {
    	WorldView.getInstance().unregisterUpdateHandler(timerHandlerMap.get(towerTile.getTower()));
    	timerHandlerMap.remove(towerTile.getTower());
    	WorldView.getInstance().runOnUpdateThread(new Runnable() {
            public void run() {
            	WorldView.getInstance().getScene().getLastChild().detachChild(TowerView.towerSpriteMap.get(towerTile.getTower()));
                
            }
        });
    }
    
    public static void setInstance(TowerController instance) {
        TowerController.instance = instance;
    }

    public static void upgradeTower(TowerTile towerTile) {
        //future
    }
}
