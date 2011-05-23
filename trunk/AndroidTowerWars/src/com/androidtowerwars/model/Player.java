package com.androidtowerwars.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Player extends Observable {
	
	public static Map<Team, Player> playerMap = new HashMap<Team, Player>();

	private int gold = 80;
	private Team team;

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
}
