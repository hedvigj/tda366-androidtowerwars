package com.androidtowerwars;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
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


import com.androidtowerwars.Preferences;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.androidtowerwars.controller.DragAndZoomController;
import com.androidtowerwars.controller.MenuController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.controller.WallController;
import com.androidtowerwars.model.TestTower;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.WorldView;

public class GameActivity extends BaseGameActivity implements IOnAreaTouchListener{

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	public static GameActivity instance = null;
	private Texture mBackgroundTexture;
	private Texture mSkeletonTexture;
	private Texture mTowerTexture;
	private Texture mButtonTexture;
	private Texture mWallTexture;
	private TextureRegion mButtonTextureRegion;
	private TextureRegion mBackgroundTextureRegion;
	public TextureRegion mSkeletonTextureRegion;	
	private TextureRegion mTowerTextureRegion;
	private TextureRegion mWallTextureRegion;
	public SoldierController soldierController;
	public TowerController towerController;
	public WallController wallController;
	
	private ClickButton s1Button;
	private ClickButton s2Button;
	private ClickButton b1Button;
	private ClickButton b2Button;
	private Sprite tower;
	private Sprite tower1;
	private Sprite b1Tower;
	private Sprite b2Tower;
	private Wall goodWall;
	private Wall badWall;

	
	MenuView menuView = new MenuView(this);
	MenuController menuController = new MenuController(this, menuView);
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
		this.mTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				this.mTowerTexture, this, "gfx/tower1.png",
				0, 0);
		this.mWallTexture = new Texture(256,256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mWallTextureRegion = TextureRegionFactory.createFromAsset(
				this.mWallTexture, this, "gfx/wall.png", 0,0);
		this.mButtonTexture = new Texture(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        
		mButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mButtonTexture, this, "gfx/GroundTile.png", 0,0);
		
        this.mEngine.getTextureManager().loadTexture(this.mButtonTexture);
		getEngine().getTextureManager().loadTexture(this.mBackgroundTexture);
		getEngine().getTextureManager().loadTexture(this.mSkeletonTexture);
		getEngine().getTextureManager().loadTexture(this.mTowerTexture);
		getEngine().getTextureManager().loadTexture(this.mWallTexture);

    
      

	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		soldierController = new SoldierController();
		scene.getLastChild().attachChild(soldierController);
		towerController = TowerController.getInstance();
		scene.getLastChild().attachChild(towerController);
		wallController =  new WallController();
		scene.getLastChild().attachChild(wallController);
		getEngine().registerUpdateHandler(new FPSLogger());
		
		
		menuView.createMenuScene();
		
		/* Limit scene size */
		WorldView.getInstance().getCamera().setBounds(0, WorldView.MAP_WIDTH, 0, WorldView.MAP_HEIGHT);
		WorldView.getInstance().getCamera().setBoundsEnabled(true);
		/* Center camera */
		WorldView.getInstance().getCamera().setCenter(WorldView.MAP_WIDTH * 0.5f,
				WorldView.MAP_HEIGHT * 0.5f);

		Scene.IOnSceneTouchListener touchListener = new DragAndZoomController(
				WorldView.getInstance().getCamera());

		scene.setOnSceneTouchListener(touchListener);
		final Sprite background = new Sprite(0, 0,
				this.mBackgroundTextureRegion);
		scene.getLastChild().attachChild(background);
		
		//TODO:Inte riktigt klart
		final Sprite bWall = new Sprite(WorldView.MAP_WIDTH, WorldView.MAP_HEIGHT * 0.3f, mWallTextureRegion);
		scene.getLastChild().attachChild(bWall);
		final Sprite gWall = new Sprite(WorldView.MAP_WIDTH-1525, WorldView.MAP_HEIGHT*0.3f, mWallTextureRegion);
		scene.getLastChild().attachChild(gWall);
		badWall = new Wall(WorldView.MAP_WIDTH, WorldView.MAP_HEIGHT * 0.3f, 300, Team.EVIL);
		goodWall = new Wall(WorldView.MAP_WIDTH-1525, WorldView.MAP_HEIGHT*0.3f, 300, Team.GOOD);

		soldierController.createSpriteSpawnTimeHandler();
		soldierController.createRightSpawnTimeHandler();
		
		scene.setOnAreaTouchListener(this);
        s1Button = new ClickButton(WorldView.MAP_WIDTH - 400, WorldView.MAP_HEIGHT * 0.4f , mButtonTextureRegion);
        s2Button = new ClickButton(WorldView.MAP_WIDTH - 400, WorldView.MAP_HEIGHT * 0.6f , mButtonTextureRegion);
        b1Button = new ClickButton(300, WorldView.MAP_HEIGHT*0.4f, mButtonTextureRegion);
        b2Button = new ClickButton(300, WorldView.MAP_HEIGHT*0.6f, mButtonTextureRegion);

        
        scene.registerTouchArea(b1Button);
        scene.registerTouchArea(b2Button);
        scene.registerTouchArea(s1Button);
        scene.registerTouchArea(s2Button);
		scene.getLastChild().attachChild(s1Button);
		scene.getLastChild().attachChild(s2Button);
        scene.getLastChild().attachChild(b1Button);
        scene.getLastChild().attachChild(b2Button);

		return scene;
	}

	public void onLoadComplete() {
		//Toast.makeText(getApplicationContext(), "The Skeleton wants to be touched!", Toast.LENGTH_LONG).show();
	}

     


     // ===========================================================
     // Methods
     // ===========================================================
		@Override
	   public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
	        if(menuController.onKeyDown(pKeyCode, pEvent)) {    
	                return true;
	        } else {
	                return super.onKeyDown(pKeyCode, pEvent);
	        }
	}

	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
			
			if(pTouchArea == s1Button){
			    
		         Intent i = new Intent(this, Pop.class);
		            startActivity(i);
		        /*
				if (tower == null) {
					Log.d("TowerWars", "s1Button");
					tower = TowerController.createTestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower);
				}
				*/
				
			}
			else if(pTouchArea == s2Button ){
				if (tower1 == null) {
					Log.d("TowerWars", "s2Button");
					tower1 = TowerController.createTestTower(WorldView.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower1);
				}
			}
			else if(pTouchArea == b1Button){
				if (b1Tower == null) {
					b1Tower = TowerController.createTestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);//new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
					getEngine().getScene().getLastChild().attachChild(b1Tower);
				}
			}
			else{
				if (b2Tower == null) {
					b2Tower = TowerController.createTestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL); //new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
					getEngine().getScene().getLastChild().attachChild(b2Tower);
				}
			}
			
		return false;
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}