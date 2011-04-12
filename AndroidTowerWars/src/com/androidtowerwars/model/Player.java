package com.androidtowerwars.model;

public class Player {

	private int gold;
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
}
