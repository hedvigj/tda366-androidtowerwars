package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.androidtowerwars.controller.ArtificialIntelligenceController;
import com.androidtowerwars.controller.DragAndZoomController;
import com.androidtowerwars.controller.MenuController;
import com.androidtowerwars.controller.PlayerController;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TouchController;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.controller.TowerTileController;
import com.androidtowerwars.controller.WallController;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.BackgroundView;
import com.androidtowerwars.view.ButtonView;
import com.androidtowerwars.view.CoinView;
import com.androidtowerwars.view.FontView;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.ProjectileView;
import com.androidtowerwars.view.RangerView;
import com.androidtowerwars.view.SoldierView;
import com.androidtowerwars.view.TouchView;
import com.androidtowerwars.view.TowerTileView;
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
	private TowerTileView towerTileView = new TowerTileView();
	private WallView wallView = new WallView();

	private MenuView menuView = new MenuView();
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
		
		MenuView.loadResources(this);
		TouchView.loadResources(this);
		TowerView.loadResources(this);
		ButtonView.loadResources(this);
		SoldierView.loadResources(this);
		RangerView.loadResources(this);
		CoinView.loadResources(this);
		ProjectileView.loadResources(this);
		WallView.loadResources(this);
		BackgroundView.loadResources(this);
		FontView.loadResources();
	}

	public Scene onLoadScene() {
		
		final Scene scene = new Scene(1);
		getEngine().registerUpdateHandler(new FPSLogger());
		
		BackgroundView.loadScene(scene);
		wallView.loadScene(scene);
		
		playerController = new PlayerController();
		soldierController = new SoldierController();
		scene.getLastChild().attachChild(soldierController);
		projectileController = new ProjectileController();
		scene.getLastChild().attachChild(projectileController);
		wallController = new WallController(WallView.goodWall, WallView.badWall);
		scene.getLastChild().attachChild(wallController);
		towerController = TowerController.getInstance();
		towerTileController = new TowerTileController();
		
		
		TouchView.loadScene(scene);
		
		menuView.createMenuScene();
		menuController.loadScene();

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
		
		WorldView.getInstance().getCamera().getHUD().setOnAreaTouchListener(touchController);

		towerTileView.loadScene(scene);
		
		scene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true,
				new ITimerCallback() {

					public void onTimePassed(final TimerHandler pTimerHandler) {
						TouchView.goldText.setText(Integer.toString(Player.playerMap.get(World.Team.GOOD)
								.getGold()));
					}
				}));
		return scene;
	}

	public void onLoadComplete() {
		Log.d("TowerWars", "gold: "+ Player.playerMap.get(World.Team.EVIL).getGold());
		ArtificialIntelligenceController.buildTower();
		ArtificialIntelligenceController.buildTower();
		ArtificialIntelligenceController.trainSoldier();
		Log.d("TowerWars", "gold: "+ Player.playerMap.get(World.Team.EVIL).getGold());
		
		Toast.makeText(
				getApplicationContext(),
				"In the land of Agarwaen the war has been raging for 300 years.",
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