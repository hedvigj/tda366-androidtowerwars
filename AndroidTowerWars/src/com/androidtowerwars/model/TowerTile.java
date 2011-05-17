package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.view.ButtonView;
import com.androidtowerwars.view.TowerView;

public class TowerTile implements IButtonSprite{
	private float x;
	private float y;
	private Rectangle area;
	private World.Team team;
	private boolean occupied = false;
	
	public TowerTile(float pX, float pY, float width, float heigth, World.Team team) {
		this.x = pX;
		this.y = pY;
		this.area = new Rectangle(x, y, width, heigth);
		this.team = team;
	}
	
	public Rectangle getRectangle() {
		return area;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public World.Team getTeam() {
		return team;
	}
	
	public boolean isOccupied() {
		return occupied;
	}	
	
	public void action() {
		if (!occupied) {
			TowerController.createTestTower(x-(TowerView.mTowerTextureRegion.getWidth() / 2)+(ButtonView.mButtonTextureRegion.getWidth() / 2),
											y-TowerView.mTowerTextureRegion.getHeight()+ButtonView.mButtonTextureRegion.getHeight(), 
											TowerView.mTowerTextureRegion, 200, team);
			occupied = true;
		}
	}
}
