package com.androidtowerwars.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Player extends Observable {
	
	public static Map<World.Team, Player> playerMap = new HashMap<World.Team, Player>();

	private int gold = 80;
	private World.Team team;

	public Player(World.Team team) {
		this.team = team;
		
	}

	public int getGold() {
		return gold;
	}

	public World.Team getTeam() {
		return team;
	}

	public void setGold(int gold) {
		this.gold = gold;

	}

	public void setTeam(World.Team team) {
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
}
