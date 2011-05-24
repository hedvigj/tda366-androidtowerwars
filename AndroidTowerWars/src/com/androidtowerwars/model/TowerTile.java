package com.androidtowerwars.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.anddev.andengine.entity.primitive.Rectangle;

import android.util.Log;

import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.view.ButtonView;
import com.androidtowerwars.view.TowerView;

public class TowerTile implements IButtonSprite {
	

	
	
	private float x;
	private float y;
	private Rectangle area;
	private Team team;
	private boolean occupied = false;
	private Tower tower;

	public TowerTile(float pX, float pY, float width, float heigth,
			Team team) {
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

	public Team getTeam() {
		return team;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void action() {
		if (!occupied) {
			this.tower = TowerController.createTestTower(
					x - (TowerView.mTowerTextureRegion.getWidth() / 2)
							+ (ButtonView.mButtonTextureRegion.getWidth() / 2),
					y - TowerView.mTowerTextureRegion.getHeight()
							+ ButtonView.mButtonTextureRegion.getHeight(),
					TowerView.mTowerTextureRegion, 200, team);
			occupied = true;
			
		}
	}

	public void magma_action() {
		if (!occupied) {
			this.tower = TowerController.createMagmaPitTower(x
					- (TowerView.mMagmaPitTowerTextureRegion.getWidth() / 2)
					+ (ButtonView.mButtonTextureRegion.getWidth() / 2) + 30, y,
					TowerView.mMagmaPitTowerTextureRegion, 200, team);
			occupied = true;
		}
	}
	
	public Tower getTower() {
	    Log.d("test","null h�r?");
	    return tower;
	}
}
