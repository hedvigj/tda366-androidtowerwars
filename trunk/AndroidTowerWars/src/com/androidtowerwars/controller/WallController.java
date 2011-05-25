package com.androidtowerwars.controller;

import java.util.List;

import org.anddev.andengine.entity.Entity;

import com.androidtowerwars.model.IWall;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.logic.WallLogic;

public class WallController extends Entity {
	
	private static WallController instance = null;
	public Wall goodWall;
	public Wall badWall;
	
	public WallController(Wall goodWall, Wall badWall) {
		this.goodWall = goodWall;
		this.badWall = badWall;
	}
	protected synchronized void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		WallLogic.updateWall(goodWall, pSecondsElapsed);
		WallLogic.updateWall(badWall, pSecondsElapsed);
		}
}
