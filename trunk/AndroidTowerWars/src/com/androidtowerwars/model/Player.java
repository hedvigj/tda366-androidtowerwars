package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends Observable implements IPlayer{
	
	private List<TowerTile> towerTileList = new ArrayList<TowerTile>();

	
	private int gold = 80;
	private Team team;
	private Barrack barrack = new Barrack();
	
	
	
//	public boolean removeTower(ITower tower) {
//		return towerList.remove(tower);
//	}
//	
//	public void addTower(Tower tower){
//		towerList.add(tower);
//	}
//	
//	public void putTowerList(List<ITower> tower){
//		towerList = new CopyOnWriteArrayList<ITower>(tower);
//	}
	
	public List<TowerTile> getTowerTiles() {
		return towerTileList;
	}
	
	public void addTowerTiles(TowerTile towerTile){
		towerTileList.add(towerTile);
	}
	
	public void putTowerTileList(List<TowerTile> towerTiles){
		towerTileList = new CopyOnWriteArrayList<TowerTile>(towerTiles);
	}

	public Player(Team team) {
		this.team = team;
		
	}

	public int getGold() {
		return gold;
	}

	public Team getTeam() {
		return team;
	}

	public void setGold(int gold) {
		this.gold = gold;

	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void decreaseGold(int cost){
		gold = gold-cost;
	}
	public void increaseGold(int earnings){
		gold = gold + earnings;
		setChanged();
		notifyObservers();
	}
	
	public void updateAI() {
		setChanged();
		notifyObservers();
		return;
	}
	public Barrack getBarrack(){
		return barrack;
	}
	

}
