package com.androidtowerwars.model;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.view.WorldView;

public class TowerTile implements IButtonSprite	{
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
			TowerController.createTestTower(x-(GameActivity.instance.mTowerTextureRegion.getWidth() / 2)+(GameActivity.instance.mButtonTextureRegion.getWidth() / 2),
											y-GameActivity.instance.mTowerTextureRegion.getHeight()+GameActivity.instance.mButtonTextureRegion.getHeight(), 
											GameActivity.instance.mTowerTextureRegion, 200, team);
			occupied = true;
		}
	}

	
	
}
