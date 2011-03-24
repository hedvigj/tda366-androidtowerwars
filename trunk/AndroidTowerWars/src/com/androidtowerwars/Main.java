package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;


public class Main extends BaseGameActivity {
	
    // ===========================================================
    // Constants
    // ===========================================================
	
	private static final int CAMERA_WIDTH = 800;
    private static final int CAMERA_HEIGHT = 480;
    private static final int MAP_WIDTH = 2000;
    private static final int MAP_HEIGHT = 480;
    
    // ===========================================================
    // Fields
    // ===========================================================
    
    private ZoomCamera mCamera;
    private Texture mTexture;
    private TextureRegion mSkeletonTextureRegion;

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


	public Engine onLoadEngine() {
		this.mCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        Engine engine = new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                this.mCamera));
        
        // Attempt to set up multitouch support
        if (MultiTouch.isSupported(this) && (MultiTouch.isSupported(this))) {
        	try {
        		engine.setTouchController(new MultiTouchController());
        	} catch (MultiTouchException e) {
        		Log.e("TowerWars", "Error with multitouch initialization", e);
        	}
        }
        return engine;
	}

	public void onLoadResources() {
		this.mTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mSkeletonTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "gfx/skeleton_master_wizard.png", 0, 0);

        this.mEngine.getTextureManager().loadTexture(this.mTexture);
	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		this.mEngine.registerUpdateHandler(new FPSLogger());
		/* Limit scene size */
		this.mCamera.setBounds(0,MAP_WIDTH,0,MAP_HEIGHT);
		this.mCamera.setBoundsEnabled(true);
		/* Center camera */
		this.mCamera.setCenter(MAP_WIDTH / 2, MAP_HEIGHT / 2);
		
		
		Scene.IOnSceneTouchListener touchListener = new DragAndZoomController(mCamera);
		
		scene.setOnSceneTouchListener(touchListener);
        scene.setBackground(new ColorBackground(0.08f, 0.6f, 0.8f));
        
        /* Calculate the coordinates for the face, so its centered on the scene. */
        final int centerX = (MAP_WIDTH - this.mSkeletonTextureRegion.getWidth()) / 2;
        final int centerY = (MAP_HEIGHT - this.mSkeletonTextureRegion.getHeight()) / 2;

        /* Create the skeleton and add it to the scene. */
        final Sprite skeleton = new Sprite(centerX, centerY, this.mSkeletonTextureRegion);
        scene.getLastChild().attachChild(skeleton);
        
        return scene;
	}

	public void onLoadComplete() {
		
	}
	
	// ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
	
}