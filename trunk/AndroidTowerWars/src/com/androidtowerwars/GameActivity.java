package com.androidtowerwars;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
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

import android.util.Log;
import android.app.Dialog;
import android.view.KeyEvent;
import android.widget.Toast;

import com.androidtowerwars.controller.DragAndZoomController;
import com.androidtowerwars.controller.MenuController;
import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.controller.WallController;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.BuildTowerView;
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
	private Texture mArrowTexture;
	private Texture mTowerTexture;
	private Texture mButtonTexture;
	private Texture mWallTexture;
	private TextureRegion mButtonTextureRegion;
	private TextureRegion mBackgroundTextureRegion;
	public TextureRegion mSkeletonTextureRegion;
	public TextureRegion mArrowTextureRegion;
	private TextureRegion mTowerTextureRegion;
	private TextureRegion mWallTextureRegion;
	public SoldierController soldierController;
	public ProjectileController projectileController;
	public TowerController towerController;
	public WallController wallController;
	private Texture mGoodBarrackTexture;
	private TextureRegion mGoodBarrackTextureRegion;
	
	private ClickButton goodButton1;
	private ClickButton goodButton2;
	private ClickButton goodButton3;
	private ClickButton goodButton4;
	private ClickButton goodButton5;
	private ClickButton goodButton6;
	private ClickButton goodButton7;
	private ClickButton goodButton8;
	private ClickButton goodButton9;
	private ClickButton goodButton10;
	private ClickButton goodButton11;
	private ClickButton goodButton12;
	
	private ClickButton badButton1;
	private ClickButton badButton2;
	private ClickButton badButton3;
	private ClickButton badButton4;
	private ClickButton badButton5;
	private ClickButton badButton6;
	private ClickButton badButton7;
	private ClickButton badButton8;
	private ClickButton badButton9;
	private ClickButton badButton10;
	private ClickButton badButton11;
	private ClickButton badButton12;
	
	private Sprite tower;
	private Sprite tower1;
	private Sprite b1Tower;
	private Sprite b2Tower;
	private Sprite goodBarrack;
	private Sprite badBarrack;
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
		this.mArrowTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mArrowTextureRegion = TextureRegionFactory.createFromAsset(
				this.mArrowTexture, this, "gfx/Arrow2.png",
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
		
		this.mGoodBarrackTexture = new Texture(512,256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mGoodBarrackTextureRegion = TextureRegionFactory.createFromAsset(
				this.mGoodBarrackTexture, this,"gfx/Good_Barracks.png", 0,0);
		
        this.mEngine.getTextureManager().loadTexture(this.mButtonTexture);
		getEngine().getTextureManager().loadTexture(this.mBackgroundTexture);
		getEngine().getTextureManager().loadTexture(this.mSkeletonTexture);
		getEngine().getTextureManager().loadTexture(this.mArrowTexture);
		getEngine().getTextureManager().loadTexture(this.mTowerTexture);
		getEngine().getTextureManager().loadTexture(this.mWallTexture);
		getEngine().getTextureManager().loadTexture(this.mGoodBarrackTexture);

    
      

	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		getEngine().registerUpdateHandler(new FPSLogger());
		final Sprite background = new Sprite(0, 0,
				this.mBackgroundTextureRegion);
		scene.getLastChild().attachChild(background);
		
		//TODO:Inte riktigt klart
		final Sprite gWall = new Sprite(WorldView.MAP_WIDTH - mWallTextureRegion.getWidth(), WorldView.MAP_HEIGHT * 0.3f, mWallTextureRegion);
		scene.getLastChild().attachChild(gWall);
		final Sprite bWall = new Sprite(0, WorldView.MAP_HEIGHT*0.3f, mWallTextureRegion);
		scene.getLastChild().attachChild(bWall);
		goodWall = new Wall(WorldView.MAP_WIDTH, WorldView.MAP_HEIGHT * 0.5f, mWallTextureRegion.getWidth(), Team.GOOD);
		badWall = new Wall(0, WorldView.MAP_HEIGHT*0.5f, mWallTextureRegion.getWidth(), Team.EVIL);
		
		soldierController = new SoldierController();
		scene.getLastChild().attachChild(soldierController);
		projectileController = new ProjectileController();
		scene.getLastChild().attachChild(projectileController);
		towerController = TowerController.getInstance();
		//scene.getLastChild().attachChild(towerController);
		wallController =  new WallController(goodWall, badWall);
		scene.getLastChild().attachChild(wallController);
		
		menuView.createMenuScene();
		
		/* Limit scene size */
		WorldView.getInstance().getCamera().setBounds(0, WorldView.MAP_WIDTH, 0, WorldView.MAP_HEIGHT);
		WorldView.getInstance().getCamera().setBoundsEnabled(true);
		/* Center camera */
		WorldView.getInstance().getCamera().setCenter(WorldView.MAP_WIDTH * 0.88f,
				WorldView.MAP_HEIGHT * 0.5f);

		Scene.IOnSceneTouchListener touchListener = new DragAndZoomController(
				WorldView.getInstance().getCamera());

		scene.setOnSceneTouchListener(touchListener);

		soldierController.createSpriteSpawnTimeHandler();
		soldierController.createRightSpawnTimeHandler();
		
		
		
		scene.setOnAreaTouchListener(this);
		// TODO: Below here everything looks horribly bad, can make some sort of GroundTile class/method?
        goodButton1 = new ClickButton(WorldView.MAP_WIDTH - 700, WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
        goodButton2 = new ClickButton(WorldView.MAP_WIDTH - 700, WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
        goodButton3 = new ClickButton(WorldView.MAP_WIDTH - 900, WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
        goodButton4 = new ClickButton(WorldView.MAP_WIDTH - 900, WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
        goodButton5 = new ClickButton(WorldView.MAP_WIDTH - 1100, WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
        goodButton6 = new ClickButton(WorldView.MAP_WIDTH - 1100, WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
        goodButton7 = new ClickButton(WorldView.MAP_WIDTH - 1300, WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
        goodButton8 = new ClickButton(WorldView.MAP_WIDTH - 1300, WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
        goodButton9 = new ClickButton(WorldView.MAP_WIDTH - 1500, WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
        goodButton10 = new ClickButton(WorldView.MAP_WIDTH - 1500, WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
        goodButton11 = new ClickButton(WorldView.MAP_WIDTH - 1700, WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
        goodButton12 = new ClickButton(WorldView.MAP_WIDTH - 1700, WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
        
        
        badButton1 = new ClickButton(500, WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
        badButton2 = new ClickButton(500, WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
        badButton3 = new ClickButton(700, WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
        badButton4 = new ClickButton(700, WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
        badButton5 = new ClickButton(900, WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
        badButton6 = new ClickButton(900, WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
        badButton7 = new ClickButton(1100, WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
        badButton8 = new ClickButton(1100, WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
        badButton9 = new ClickButton(1300, WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
        badButton10 = new ClickButton(1300, WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
        badButton11 = new ClickButton(1500, WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
        badButton12 = new ClickButton(1500, WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
        
        goodBarrack = new ClickButton(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        badBarrack = new ClickButton(100, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        
        scene.registerTouchArea(goodButton1);
        scene.registerTouchArea(goodButton2);
        scene.registerTouchArea(goodButton3);
        scene.registerTouchArea(goodButton4);
        scene.registerTouchArea(goodButton5);
        scene.registerTouchArea(goodButton6);
        scene.registerTouchArea(goodButton7);
        scene.registerTouchArea(goodButton8);
        scene.registerTouchArea(goodButton9);
        scene.registerTouchArea(goodButton10);
        scene.registerTouchArea(goodButton11);
        scene.registerTouchArea(goodButton12);
        
        scene.registerTouchArea(badButton1);
        scene.registerTouchArea(badButton2);
        scene.registerTouchArea(badButton3);
        scene.registerTouchArea(badButton4);
        scene.registerTouchArea(badButton5);
        scene.registerTouchArea(badButton6);
        scene.registerTouchArea(badButton7);
        scene.registerTouchArea(badButton8);
        scene.registerTouchArea(badButton9);
        scene.registerTouchArea(badButton10);
        scene.registerTouchArea(badButton11);
        scene.registerTouchArea(badButton12);
        
        scene.registerTouchArea(goodBarrack);
        scene.registerTouchArea(badBarrack);
        
        
        scene.getLastChild().attachChild(goodButton1);
		scene.getLastChild().attachChild(goodButton2);
		scene.getLastChild().attachChild(goodButton3);
		scene.getLastChild().attachChild(goodButton4);
		scene.getLastChild().attachChild(goodButton5);
		scene.getLastChild().attachChild(goodButton6);
		scene.getLastChild().attachChild(goodButton7);
		scene.getLastChild().attachChild(goodButton8);
		scene.getLastChild().attachChild(goodButton9);
		scene.getLastChild().attachChild(goodButton10);
		scene.getLastChild().attachChild(goodButton11);
		scene.getLastChild().attachChild(goodButton12);
        
        scene.getLastChild().attachChild(badButton2);
        scene.getLastChild().attachChild(badButton1);
        scene.getLastChild().attachChild(badButton3);
        scene.getLastChild().attachChild(badButton4);
        scene.getLastChild().attachChild(badButton5);
        scene.getLastChild().attachChild(badButton6);
        scene.getLastChild().attachChild(badButton7);
        scene.getLastChild().attachChild(badButton8);
        scene.getLastChild().attachChild(badButton9);
        scene.getLastChild().attachChild(badButton10);
        scene.getLastChild().attachChild(badButton11);
        scene.getLastChild().attachChild(badButton12);
        
        scene.getLastChild().attachChild(goodBarrack);
        scene.getLastChild().attachChild(badBarrack);
		return scene;
	}

	public void onLoadComplete() {
		Toast.makeText(getApplicationContext(), "In the land of Agarwaen the war has been raging for 30 years.", Toast.LENGTH_LONG).show();
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
			
			if(pTouchArea == goodButton1){
			    
		         //Intent i = new Intent(this, PopupPad.class);
		           // startActivity(i);
		         Dialog v = new BuildTowerView(this);
		         v.show();
		        /*
				if (tower == null) {
					Log.d("TowerWars", "s1Button");
					tower = TowerController.createTestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower);
				}
				*/
				
			}
			else if(pTouchArea == goodButton2 ){
				if (tower1 == null) {
					Log.d("TowerWars", "s2Button");
					tower1 = TowerController.createTestTower(WorldView.MAP_WIDTH - 700 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower1);
				}
			}
			else if(pTouchArea == badButton1){
				if (b1Tower == null) {
					b1Tower = TowerController.createTestTower(500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.44f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);//new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
					getEngine().getScene().getLastChild().attachChild(b1Tower);
				}
			}
			else{
				if (b2Tower == null) {
					b2Tower = TowerController.createTestTower(500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL); //new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
					getEngine().getScene().getLastChild().attachChild(b2Tower);
				}
			}
			
		return false;
		
	}

    public void buildTower() {
        
        tower = TowerController.createTestTower(WorldView.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
        getEngine().getScene().getLastChild().attachChild(tower);
    }

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}