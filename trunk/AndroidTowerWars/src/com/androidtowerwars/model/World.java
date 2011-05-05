package com.androidtowerwars.model;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;

import android.util.Log;


public class World{
	
    public enum Team {
    	 GOOD, EVIL;
    	 
    	 public Team opposite() {
    		 if (this == Team.EVIL)
    			 return Team.GOOD;
    		 else
    			 return Team.EVIL;
    	 }
    	}
}    

