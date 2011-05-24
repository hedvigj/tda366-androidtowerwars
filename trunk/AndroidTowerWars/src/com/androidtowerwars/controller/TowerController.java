package com.androidtowerwars.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.MagmaPitTower;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.TowerLogic;
import com.androidtowerwars.view.TowerView;
import com.androidtowerwars.view.WorldView;

public class TowerController {//extends Entity {
	
	private static TowerController instance = null;
	private static List<TimerHandler> timerHandlerList = new ArrayList<TimerHandler>();
	private static TimerHandler timerHandler;
	
	public TowerController() {
		World.getInstance().getPlayer(Team.GOOD).getTowerTile().putTowerList(new CopyOnWriteArrayList<ITower>());
		World.getInstance().getPlayer(Team.EVIL).getTowerTile().putTowerList(new CopyOnWriteArrayList<ITower>());
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
        World.getInstance().getPlayer(team).getTowerTile().addTowers(tower);
       	TowerView.towerSpriteMap.put(tower, sprite);
        GameActivity.setTimestamp(0);
        WorldView.getInstance().getScene().getLastChild().attachChild(sprite);
                timerHandler = new TimerHandler(tower.getAttackSpeed(),
                        new ITimerCallback() {
                            public void onTimePassed(
                                    final TimerHandler pTimerHandler) {
                                pTimerHandler.reset();
                                TowerLogic.updateTower(tower);
                            }
                        });
                timerHandlerList.add(timerHandler);
        WorldView.getInstance().registerUpdateHandler(timerHandler);
        World.playerMap.get(team).decreaseGold(tower.getCost());
        return tower;
    }
    public static synchronized Tower createMagmaPitTower(float pX, float pY, TextureRegion pTextureRegion, float range, Team team) {
        final MagmaPitTower tower = new MagmaPitTower(pX, pY, range, team);
        Sprite sprite = new Sprite(pX, pY, pTextureRegion);
        World.getInstance().getPlayer(team).getTowerTile().addTowers(tower);
        TowerView.towerSpriteMap.put(tower, sprite);
        GameActivity.setTimestamp(0);
        WorldView.getInstance().getScene().getLastChild().attachChild(sprite);
                timerHandler = new TimerHandler(tower.getAttackSpeed(),
                        new ITimerCallback() {
                            public void onTimePassed(
                                    final TimerHandler pTimerHandler) {
                                pTimerHandler.reset();
                                TowerLogic.updateTower(tower);
                            }
                        });
                timerHandlerList.add(timerHandler);
        WorldView.getInstance().registerUpdateHandler(timerHandler);
        World.playerMap.get(team).decreaseGold(tower.getCost());
        return tower;
    }

    public static synchronized void sellTower(final TowerTile towerTile) {
        int i = (int) ((int) towerTile.getTower().getCost()*0.75);
        World.playerMap.get(towerTile.getTeam()).increaseGold(i);
        removeTower(towerTile);
    }
    
    public static synchronized void removeTower(final TowerTile towerTile) {
        WorldView.getInstance().runOnUpdateThread(new Runnable() {
            public void run() {
                World.getInstance().getPlayer(towerTile.getTeam()).getTowerTile().getTowers().remove(towerTile.getTower());
                World.getInstance().getPlayer(towerTile.getTeam()).getTowerTiles().remove(towerTile);
                WorldView.getInstance().getScene().getLastChild().detachChild(TowerView.towerSpriteMap.get(towerTile.getTower()));
            }
        });
    }
    
    public static void setInstance(TowerController instance) {
        TowerController.instance = instance;
    }
}
