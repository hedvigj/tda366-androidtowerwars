package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.androidtowerwars.controller.DragAndZoomController;
import com.androidtowerwars.controller.MenuController;
import com.androidtowerwars.controller.PlayerController;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TouchController;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.controller.TowerTileController;
import com.androidtowerwars.controller.WallController;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.model.logic.ArtificialIntelligence;
import com.androidtowerwars.view.BackgroundView;
import com.androidtowerwars.view.ButtonView;
import com.androidtowerwars.view.ClickButton;
import com.androidtowerwars.view.CoinView;
import com.androidtowerwars.view.FontView;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.ProjectileView;
import com.androidtowerwars.view.SoldierView;
import com.androidtowerwars.view.TouchView;
import com.androidtowerwars.view.TowerView;
import com.androidtowerwars.view.WallView;
import com.androidtowerwars.view.WorldView;

public class GameActivity extends BaseGameActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public static GameActivity instance = null;

	public SoldierController soldierController;
	public ProjectileController projectileController;
	public TowerController towerController;
	public WallController wallController;
	public TowerTileController towerTileController;
	public PlayerController playerController;
	
	private static long timestamp;

	private TouchController touchController = new TouchController(this);
	private TouchView touchView = new TouchView(this);
	private ButtonView buttonView = new ButtonView(this);
	private TowerView towerView = new TowerView(this);
	private SoldierView soldierView = new SoldierView(this);
	private WallView wallView = new WallView(this);
	private CoinView coinView = new CoinView(this);
	private BackgroundView backgroundView = new BackgroundView(this);
	private ProjectileView projectileView = new ProjectileView(this);
	private MenuView menuView = new MenuView(this);
	private FontView fontView = new FontView();
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
		towerView.loadResources();
		buttonView.loadResources();
		soldierView.loadResources();
		coinView.loadResources();
		projectileView.loadResources();
		wallView.loadResources();
		backgroundView.loadResources();
		fontView.loadResources();
	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);

		getEngine().registerUpdateHandler(new FPSLogger());
		final Sprite background = new Sprite(0, 0,
				BackgroundView.mBackgroundTextureRegion);
		scene.getLastChild().attachChild(background);

		// TODO:Inte riktigt klart
		final Sprite gWall = new Sprite(WorldView.MAP_WIDTH
				- WallView.mWallTextureRegion.getWidth(), WorldView.MAP_HEIGHT * 0.3f,
				WallView.mWallTextureRegion);
		scene.getLastChild().attachChild(gWall);
		final Sprite bWall = new Sprite(0, WorldView.MAP_HEIGHT * 0.3f,
				WallView.mWallTextureRegion);
		scene.getLastChild().attachChild(bWall);
		WallView.goodWall = new Wall(WorldView.MAP_WIDTH, WorldView.MAP_HEIGHT * 0.5f,
				WallView.mWallTextureRegion.getWidth(), Team.GOOD);
		WallView.badWall = new Wall(0, WorldView.MAP_HEIGHT * 0.5f,
				WallView.mWallTextureRegion.getWidth(), Team.EVIL);

		playerController = new PlayerController();
		soldierController = new SoldierController();
		scene.getLastChild().attachChild(soldierController);
		projectileController = new ProjectileController();
		scene.getLastChild().attachChild(projectileController);
		wallController = new WallController(WallView.goodWall, WallView.badWall);
		scene.getLastChild().attachChild(wallController);
		towerController = TowerController.getInstance();
		towerTileController = new TowerTileController();
		
		wallView.loadScene(scene);
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
					ButtonView.mButtonTextureRegion);
			TowerTile tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.44f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTile.towerTileListMap.get(World.Team.GOOD).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.56f,
					ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.56f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTile.towerTileListMap.get(World.Team.GOOD).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			// evil towers
			tempSprite = new ClickButton(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.44f, ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.44f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTile.towerTileListMap.get(World.Team.EVIL).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			//scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.56f, ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.56f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTile.towerTileListMap.get(World.Team.EVIL).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			//scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
		}
		
		scene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true,
				new ITimerCallback() {

					public void onTimePassed(final TimerHandler pTimerHandler) {
						TouchView.goldText.setText(Integer.toString(PlayerController.playerMap.get(World.Team.GOOD)
								.getGold()));
					}
				}));
		return scene;
	}

	public void onLoadComplete() {
		Log.d("TowerWars", "gold: "+ PlayerController.playerMap.get(World.Team.EVIL).getGold());
		ArtificialIntelligence.buildTower();
		ArtificialIntelligence.buildTower();
		ArtificialIntelligence.trainSoldier();
		Log.d("TowerWars", "gold: "+ PlayerController.playerMap.get(World.Team.EVIL).getGold());
		
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