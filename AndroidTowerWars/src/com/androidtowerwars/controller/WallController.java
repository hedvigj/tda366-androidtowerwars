package com.androidtowerwars.controller;

import java.util.List;

import org.anddev.andengine.entity.Entity;

import com.androidtowerwars.model.IWall;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.logic.WallLogic;

public class WallController extends Entity {
	
	private static WallController instance = null;
	
	protected synchronized void onManagedUpdate(Wall wall, final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		WallLogic.updateWall(wall, pSecondsElapsed);
		}
}
