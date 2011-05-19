package com.androidtowerwars.controller;

	import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import android.util.Log;

import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.ArtificialIntelligence;
import com.androidtowerwars.view.WorldView;

	public class ArtificialIntelligenceController implements Observer{
	
		private static World.Team team;
		private static float updateInterval = 0.5f;
		private static int nBuiltTowers = 0;
		
		
		public ArtificialIntelligenceController(Player evilPlayer) {
			ArtificialIntelligence.evilPlayer = evilPlayer;
			team = evilPlayer.getTeam();
			ArtificialIntelligence.evilPlayer.increaseGold(30);
		}
		
		
		public static void updateByTime() {
			
			if (ArtificialIntelligence.evilRich()) {
				if (ArtificialIntelligence.evilSurpriseSmall()) {
					buildTower();
					trainSoldier();
				}
				else {
					trainSoldier();
				}
			}
			else if (ArtificialIntelligence.evilLessRich()){
				trainSoldier();
			}
			
			if (ArtificialIntelligence.evilSurpriseBig()) {
				trainSoldier();
			}
		}
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			Player model = (Player) arg0;
			
			if (ArtificialIntelligence.evilKindaPoor()) {
				if (ArtificialIntelligence.evilSurpriseMedium()) {
					buildTower();
					trainSoldier();
				}
				else {
					trainSoldier();
				}
			}
			else if(ArtificialIntelligence.evilPoor()) {
				if (ArtificialIntelligence.evilSurpriseMedium()){
					trainSoldier();
				}
				
			}
		}
		
		public static void buildTower() {
			List<TowerTile> tileList = World.towerTileListMap.get(team);
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
