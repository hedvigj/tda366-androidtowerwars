package com.androidtowerwars;

import java.util.Observable;
import java.util.Observer;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.MathUtils;

import android.util.Log;
import android.widget.Toast;

import com.androidtowerwars.constants.*;

public class Main extends BaseGameActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	public static Main instance = null;
	private ZoomCamera mCamera;
	private Texture mBackgroundTexture;
	private Texture mSkeletonTexture;
	private Texture mTowerTexture;
	private TextureRegion mBackgroundTextureRegion;
	public TextureRegion mSkeletonTextureRegion;	
	private TextureRegion mTowerTextureRegion;
	public Controller controller;
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	public Engine onLoadEngine() {
		instance = this;
		controller = new Controller();
		this.mCamera = new ZoomCamera(0, 0, Constants.CAMERA_WIDTH,
				Constants.CAMERA_HEIGHT);
		Engine engine = new Engine(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT),
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
		this.mBackgroundTexture = new Texture(2048, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mBackgroundTextureRegion = TextureRegionFactory.createFromAsset(
				this.mBackgroundTexture, this,
				"gfx/parallax_background_layer_back.png", 0, 0);
		this.mSkeletonTexture = new Texture(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mSkeletonTextureRegion = TextureRegionFactory.createFromAsset(
				this.mSkeletonTexture, this, "gfx/skeleton_master_wizard.png",
				0, 0);
		this.mTowerTexture = new Texture(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				this.mTowerTexture, this, "gfx/arrow_tower_2.png",
				0, 0);
		
		this.mEngine.getTextureManager().loadTexture(this.mBackgroundTexture);
		this.mEngine.getTextureManager().loadTexture(this.mSkeletonTexture);
		this.mEngine.getTextureManager().loadTexture(this.mTowerTexture);
	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		this.mEngine.registerUpdateHandler(new FPSLogger());
		/* Limit scene size */
		this.mCamera.setBounds(0, Constants.MAP_WIDTH, 0, Constants.MAP_HEIGHT);
		this.mCamera.setBoundsEnabled(true);
		/* Center camera */
		this.mCamera.setCenter(Constants.MAP_WIDTH * 0.5f,
				Constants.MAP_HEIGHT * 0.5f);

		Scene.IOnSceneTouchListener touchListener = new DragAndZoomController(
				mCamera);

		scene.setOnSceneTouchListener(touchListener);
		final Sprite background = new Sprite(0, 0,
				this.mBackgroundTextureRegion);
		// scene.setBackground(new SpriteBackground(background));
		scene.getLastChild().attachChild(background);

		final TestTower tower = new TestTower(Constants.MAP_WIDTH-300, Constants.MAP_HEIGHT * 0.5f, this.mTowerTextureRegion, 200);
		final BadTower bTower = new BadTower(300, Constants.MAP_HEIGHT*0.5f, this.mTowerTextureRegion, 200);
		scene.getLastChild().attachChild(tower);
		scene.getLastChild().attachChild(bTower);
		controller.createSpriteSpawnTimeHandler();
		controller.createRightSpawnTimeHandler();
		return scene;
	}

	public void onLoadComplete() {
		//Toast.makeText(getApplicationContext(), "The Skeleton wants to be touched!", Toast.LENGTH_LONG).show();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}