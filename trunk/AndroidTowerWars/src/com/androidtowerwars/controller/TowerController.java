package com.androidtowerwars.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.model.logic.TowerLogic;

import com.androidtowerwars.view.WorldView;

public class TowerController {//extends Entity {
	
	public static ConcurrentHashMap<World.Team, List<ITower>> towerListMap = new ConcurrentHashMap<World.Team, List<ITower>>();
	public static ConcurrentHashMap<ITower, Sprite> towerSpriteMap = new ConcurrentHashMap<ITower, Sprite>();
	private static TowerController instance = null;
	private static List<TimerHandler> timerHandlerList = new ArrayList<TimerHandler>();
	private static TimerHandler timerHandler;

	
	
	public TowerController() {
		towerListMap.put(Team.GOOD, new CopyOnWriteArrayList<ITower>());
		towerListMap.put(Team.EVIL, new CopyOnWriteArrayList<ITower>());
		setInstance(this);
	}
	
	public static TowerController getInstance() {
		if (instance == null) {
			instance = new TowerController(); 
		}
		return instance;
	}
	
	
    public static synchronized void createTestTower(float pX, float pY, TextureRegion pTextureRegion, float range, World.Team team) {
        final Tower tower = new Tower(pX, pY, range, team);
        Sprite sprite = new Sprite(pX, pY, pTextureRegion);
        towerListMap.get(team).add(tower);
        towerSpriteMap.put(tower, sprite);
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
        return;
    }

    public static void setInstance(TowerController instance) {
        TowerController.instance = instance;
    }
}
