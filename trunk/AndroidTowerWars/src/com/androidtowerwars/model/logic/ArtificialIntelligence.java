package com.androidtowerwars.model.logic;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import android.util.Log;

import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TowerTileController;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.WorldView;

public class ArtificialIntelligence implements Observer{
	private static Player evilPlayer;
	private static World.Team team;
	private static float updateInterval = 0.5f;
	private static int nBuiltTowers = 0;
	
	
	public ArtificialIntelligence(Player evilPlayer) {
		this.evilPlayer = evilPlayer;
		team = evilPlayer.getTeam();
		evilPlayer.increaseGold(30);
	}
	
	
	public static void updateByTime() {
		Log.d("TowerWars", "updateByTime  Cash: "+evilPlayer.getGold());
		if (evilPlayer.getGold() > 125) {
			if (Math.random() <= 0.3) {
				buildTower();
				trainSoldier();
			}
			else {
				trainSoldier();
			}
		}
		else if (evilPlayer.getGold() > 42){
			trainSoldier();
		}
		
		if (Math.random() <= 0.2) {
			trainSoldier();
		}
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Player model = (Player) arg0;
		
		if (evilPlayer.getGold() > 39) {
			if (Math.random() <= 0.25) {
				buildTower();
				trainSoldier();
			}
			else {
				trainSoldier();
			}
		}
		else if(evilPlayer.getGold() > 5) {
			if (Math.random() <= 0.25){
				trainSoldier();
			}
			
		}
	}
	
	public static void buildTower() {
		List<TowerTile> tileList = TowerTile.towerTileListMap.get(team);
		Random r = new Random();
		while (nBuiltTowers < tileList.size()) {
			TowerTile tile = tileList.get(r.nextInt(tileList.size()));
			if (!tile.isOccupied()) {
				tile.action();
				nBuiltTowers++;
				break;
			}
		}
		return;
	}
	
	public static void trainSoldier() {
		SoldierController.createSprite(300, WorldView.MAP_HEIGHT*0.52f, World.Team.EVIL);
		return;
	}
	
	public static float getUpdateInterval() {
		return updateInterval;
	}

}
