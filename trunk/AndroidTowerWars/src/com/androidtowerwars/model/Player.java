package com.androidtowerwars.model;

public class Player {

	private static int gold = 10;
	private World.Team team;

	public Player(World.Team team) {
		this.team = team;
	}

	public static int getGold() {
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
	
	public static void decreaseGold(int cost){
		gold = gold-cost;
	}
	public static void increaseGold(int earnings){
		gold = gold + earnings;
	}
}
