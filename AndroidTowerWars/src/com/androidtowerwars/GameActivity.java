package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.androidtowerwars.controller.DragAndZoomController;
import com.androidtowerwars.controller.MenuController;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TouchController;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.controller.TowerTileController;
import com.androidtowerwars.controller.WallController;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.BuildTowerView;
import com.androidtowerwars.view.ClickButton;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.TouchView;
import com.androidtowerwars.view.WorldView;

public class GameActivity extends BaseGameActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public static GameActivity instance = null;
	private Texture mBackgroundTexture;
	private Texture mSkeletonTexture;
	private Texture mArrowTexture;
	private Texture mTowerTexture;
	private Texture mButtonTexture;
	private Texture mWallTexture;
	public TextureRegion mButtonTextureRegion;
	private TextureRegion mBackgroundTextureRegion;
	public TextureRegion mSkeletonTextureRegion;
	public TextureRegion mArrowTextureRegion;
	public TextureRegion mTowerTextureRegion;
	private TextureRegion mWallTextureRegion;
	public SoldierController soldierController;
	public ProjectileController projectileController;
	public TowerController towerController;
	public WallController wallController;
	public TowerTileController towerTileController;

	private Wall goodWall;
	private Wall badWall;

	private static long timestamp;

	private TouchController touchController = new TouchController(this);
	private TouchView touchView = new TouchView(this);
	private MenuView menuView = new MenuView(this);
	private MenuController menuController = new MenuController(this, menuView);

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public Engine onLoadEngine() {
		instance = this;
		Engine engine = new WorldView();
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

		menuView.loadResources();
		touchView.loadResources();
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
		this.mArrowTexture = new Texture(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mArrowTextureRegion = TextureRegionFactory.createFromAsset(
				this.mArrowTexture, this, "gfx/Arrow2.png", 0, 0);
		this.mTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				this.mTowerTexture, this, "gfx/tower1.png", 0, 0);
		this.mWallTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mWallTextureRegion = TextureRegionFactory.createFromAsset(
				this.mWallTexture, this, "gfx/wall.png", 0, 0);
		this.mButtonTexture = new Texture(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		mButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mButtonTexture, this, "gfx/GroundTile.png", 0, 0);

		this.mEngine.getTextureManager().loadTexture(this.mButtonTexture);
		getEngine().getTextureManager().loadTexture(this.mBackgroundTexture);
		getEngine().getTextureManager().loadTexture(this.mSkeletonTexture);
		getEngine().getTextureManager().loadTexture(this.mArrowTexture);
		getEngine().getTextureManager().loadTexture(this.mTowerTexture);
		getEngine().getTextureManager().loadTexture(this.mWallTexture);

	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);

		getEngine().registerUpdateHandler(new FPSLogger());
		final Sprite background = new Sprite(0, 0,
				this.mBackgroundTextureRegion);
		scene.getLastChild().attachChild(background);

		// TODO:Inte riktigt klart
		final Sprite gWall = new Sprite(WorldView.MAP_WIDTH
				- mWallTextureRegion.getWidth(), WorldView.MAP_HEIGHT * 0.3f,
				mWallTextureRegion);
		scene.getLastChild().attachChild(gWall);
		final Sprite bWall = new Sprite(0, WorldView.MAP_HEIGHT * 0.3f,
				mWallTextureRegion);
		scene.getLastChild().attachChild(bWall);
		goodWall = new Wall(WorldView.MAP_WIDTH, WorldView.MAP_HEIGHT * 0.5f,
				mWallTextureRegion.getWidth(), Team.GOOD);
		badWall = new Wall(0, WorldView.MAP_HEIGHT * 0.5f,
				mWallTextureRegion.getWidth(), Team.EVIL);

		soldierController = new SoldierController();
		scene.getLastChild().attachChild(soldierController);
		projectileController = new ProjectileController();
		scene.getLastChild().attachChild(projectileController);
		wallController = new WallController(goodWall, badWall);
		scene.getLastChild().attachChild(wallController);
		towerController = TowerController.getInstance();
		towerTileController = new TowerTileController();

		touchView.loadScene(scene);
		menuView.createMenuScene();

		/* Limit scene size */
		WorldView.getInstance().getCamera()
				.setBounds(0, WorldView.MAP_WIDTH, 0, WorldView.MAP_HEIGHT);
		WorldView.getInstance().getCamera().setBoundsEnabled(true);
		/* Center camera */
		WorldView
				.getInstance()
				.getCamera()
				.setCenter(WorldView.MAP_WIDTH * 0.88f,
						WorldView.MAP_HEIGHT * 0.5f);

		Scene.IOnSceneTouchListener touchListener = new DragAndZoomController(
				WorldView.getInstance().getCamera());

		scene.setOnSceneTouchListener(touchListener);

		scene.setOnAreaTouchListener(touchController);

		int tileSpacing = 200;
		for (int n = 0; n < 6; n++) {
			// good towers
			ClickButton tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.44f,
					mButtonTextureRegion);
			TowerTile tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.44f,
					mButtonTextureRegion.getWidth(),
					mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTileController.towerTileListMap.get(World.Team.GOOD).add(
					tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.56f,
					mButtonTextureRegion);
			tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.56f,
					mButtonTextureRegion.getWidth(),
					mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTileController.towerTileListMap.get(World.Team.GOOD).add(
					tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			// evil towers
			tempSprite = new ClickButton(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.44f, mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.44f,
					mButtonTextureRegion.getWidth(),
					mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTileController.towerTileListMap.get(World.Team.EVIL).add(
					tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.56f, mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.56f,
					mButtonTextureRegion.getWidth(),
					mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTileController.towerTileListMap.get(World.Team.EVIL).add(
					tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
		}

		scene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true,
				new ITimerCallback() {

					public void onTimePassed(final TimerHandler pTimerHandler) {
						TouchView.goldText.setText(Integer.toString(Player
								.getGold()));
					}
				}));
		return scene;
	}

	public void onLoadComplete() {
		Toast.makeText(
				getApplicationContext(),
				"In the land of Agarwaen the war has been raging for 30 years.",
				Toast.LENGTH_LONG).show();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (menuController.onKeyDown(pKeyCode, pEvent)) {
			return true;
		} else {
			return super.onKeyDown(pKeyCode, pEvent);
		}
	}

	public static void setTimestamp(int i) {
		Log.d("TowerWars", "time" + i);
		timestamp = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}