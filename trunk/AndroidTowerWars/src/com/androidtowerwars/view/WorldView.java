package com.androidtowerwars.view;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;

import com.androidtowerwars.model.ISoldier;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;


public class WorldView extends Engine {
	
	public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public static final int MAP_WIDTH = 4000;
    public static final int MAP_HEIGHT = 2000;

	private static WorldView instance = null;
	
	public WorldView() {
		super(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						WorldView.CAMERA_WIDTH, WorldView.CAMERA_HEIGHT),
						new ZoomCamera(0, 0, WorldView.CAMERA_WIDTH,
								WorldView.CAMERA_HEIGHT)));
		setInstance(this);
	}
	
	
	public static WorldView getInstance() {
		if (instance == null) {
			instance = new WorldView(); 
		}
		return instance;
	}
	
	public ZoomCamera getCamera() {
		return (ZoomCamera) super.getCamera();
	}


    public static void setInstance(WorldView instance) {
        WorldView.instance = instance;
    }
	

}
	
