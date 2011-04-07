package com.androidtowerwars.model;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;

import android.util.Log;


public class World extends Engine {
	
	//CONSTANTS
	public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public static final int MAP_WIDTH = 1280;
    public static final int MAP_HEIGHT = CAMERA_HEIGHT;
	
    public enum Team {
    	 GOOD, EVIL;
    	 
    	 public Team opposite() {
    		 if (this == Team.EVIL)
    			 return Team.GOOD;
    		 else
    			 return Team.EVIL;
    	 }
    	}
    
	//VARIABLES
	private static World instance = null;
	
	public World() {
		super(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						World.CAMERA_WIDTH, World.CAMERA_HEIGHT),
						new ZoomCamera(0, 0, World.CAMERA_WIDTH,
								World.CAMERA_HEIGHT)));
		this.instance = this;
	}
	
	
	public static World getInstance() {
		if (instance == null) {
			instance = new World(); 
		}
		return instance;
	}
	
	public ZoomCamera getCamera() {
		return (ZoomCamera) super.getCamera();
	}
	

}