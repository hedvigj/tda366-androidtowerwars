package com.androidtowerwars.model;

public interface DefenceTower {
	void setHitpoints();
	int  getHitpoints();
	void setAttack();
	int  getAttack();
	void buildTower();
	void removeTower();
	void upgradeTower();
	void shootProjectiles();
}
