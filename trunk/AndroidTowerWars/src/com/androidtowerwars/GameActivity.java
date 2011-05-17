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
	
	private Texture mGoodBarrackTexture;
	private TextureRegion mGoodBarrackTextureRegion;
	private Texture mCoinTexture;
	private TextureRegion mCoinTextureRegion;
	private ClickButton goodBarrack;
	private ClickButton badBarrack;
	private Wall goodWall;
	private Wall badWall;
	private HUD headUpDisplay;
	private static long timestamp;
	private Sprite coin;
	private  ChangeableText goldText;
	private Font mFont;
	private Texture mFontTexture;

	
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
		
		this.mCoinTexture = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mCoinTextureRegion = TextureRegionFactory.createFromAsset(
				this.mCoinTexture, this, "gfx/Coin.png", 0,0);
		
		this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);

        this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
        this.mEngine.getFontManager().loadFont(this.mFont);

		
        this.mEngine.getTextureManager().loadTexture(this.mButtonTexture);
		getEngine().getTextureManager().loadTexture(this.mBackgroundTexture);
		getEngine().getTextureManager().loadTexture(this.mSkeletonTexture);
		getEngine().getTextureManager().loadTexture(this.mArrowTexture);
		getEngine().getTextureManager().loadTexture(this.mTowerTexture);
		getEngine().getTextureManager().loadTexture(this.mWallTexture);
		getEngine().getTextureManager().loadTexture(this.mGoodBarrackTexture);
		getEngine().getTextureManager().loadTexture(this.mCoinTexture);
    
      

	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		this.headUpDisplay = new HUD();
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
		wallController =  new WallController(goodWall, badWall);
		scene.getLastChild().attachChild(wallController);
		towerController = TowerController.getInstance();
		towerTileController = new TowerTileController();
		
		
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

		
				
		scene.setOnAreaTouchListener(this);
		
		int tileSpacing = 200;
		for(int n = 0;n<6;n++) {
			//good towers
			ClickButton tempSprite = new ClickButton(WorldView.MAP_WIDTH - (700 + (tileSpacing*n)), WorldView.MAP_HEIGHT * 0.44f , mButtonTextureRegion);
			TowerTile tempTowerTile = new TowerTile(WorldView.MAP_WIDTH - (700 + (tileSpacing*n)), WorldView.MAP_HEIGHT * 0.44f, mButtonTextureRegion.getWidth(),mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTileController.towerTileListMap.get(World.Team.GOOD).add(tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
			
			tempSprite = new ClickButton(WorldView.MAP_WIDTH - (700 + (tileSpacing*n)), WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion);
			tempTowerTile = new TowerTile(WorldView.MAP_WIDTH - (700 + (tileSpacing*n)), WorldView.MAP_HEIGHT * 0.56f , mButtonTextureRegion.getWidth(), mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTileController.towerTileListMap.get(World.Team.GOOD).add(tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
			
			//evil towers
			tempSprite = new ClickButton(500 + (tileSpacing*n), WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing*n), WorldView.MAP_HEIGHT*0.44f, mButtonTextureRegion.getWidth(), mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTileController.towerTileListMap.get(World.Team.EVIL).add(tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
			
			tempSprite = new ClickButton(500 + (tileSpacing*n), WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing*n), WorldView.MAP_HEIGHT*0.56f, mButtonTextureRegion.getWidth(), mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTileController.towerTileListMap.get(World.Team.EVIL).add(tempTowerTile);
			TowerTileController.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
		}
		
        
        goodBarrack = new ClickButton(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        badBarrack = new ClickButton(100, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        coin = new Sprite(WorldView.CAMERA_WIDTH-175, 10, mCoinTextureRegion);
        goldText = new ChangeableText(WorldView.CAMERA_WIDTH-225, 10, this.mFont, "", "XXXXX".length());

       
        scene.registerTouchArea(goodBarrack);
        scene.registerTouchArea(badBarrack);
        
        
        scene.getLastChild().attachChild(goodBarrack);
        scene.getLastChild().attachChild(badBarrack);
        headUpDisplay.getLastChild().attachChild(coin);
        headUpDisplay.getLastChild().attachChild(goldText);
        
        WorldView.getInstance().getCamera().setHUD(this.headUpDisplay);
        
        scene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
            
            public void onTimePassed(final TimerHandler pTimerHandler) {
            	 goldText.setText(Integer.toString(Player.getGold()));
            }
    }));
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
		TowerTile towerTile;
		switch(pSceneTouchEvent.getAction())
        {
                case MotionEvent.ACTION_DOWN:
                	if(pTouchArea == goodBarrack){
            			goodBarrack.touch();
            		}
            		else if(pTouchArea == badBarrack){
            			badBarrack.touch();
            		}
                    
                	towerTile = (TowerTile) TowerTileController.towerTileSpriteMap.get(pTouchArea);
            		if (towerTile != null && towerTile.isOccupied() != true) {
            		    ClickButton tileSprite = (ClickButton) pTouchArea;
            		    tileSprite.touch();
            		}
                        return false;
               
                case MotionEvent.ACTION_UP:
                	if(pTouchArea == goodBarrack){
            			if(goodBarrack.releaseTouchSuccessfull()) {
            				soldierController.createSprite(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.52f, World.Team.GOOD);            				
            			}                		
            		}
            		else if(pTouchArea == badBarrack){
            			if(badBarrack.releaseTouchSuccessfull()) {
            				soldierController.createSprite(300, WorldView.MAP_HEIGHT*0.52f, World.Team.EVIL);
            			}
            			
            		}
                	towerTile = (TowerTile) TowerTileController.towerTileSpriteMap.get(pTouchArea);
            		if (towerTile != null && towerTile.isOccupied() != true) {
            			ClickButton tileSprite = (ClickButton) pTouchArea;
            			if (tileSprite.releaseTouchSuccessfull()) {
            		        Dialog v = new BuildTowerView(this, towerTile);
            		        v.show();
            		    }
            		}
            		
                        return false;
        }
		
//			if(pTouchArea == goodButton1){
//			    Log.d("TowerWars", timestamp + "");
//			    if (timestamp != Math.round(System.currentTimeMillis() / 1000)) {
//			        timestamp = Math.round(System.currentTimeMillis() / 1000);
//			        Log.d("TowerWars", "insidan");
//			        Dialog v = new BuildTowerView(this, WorldView.MAP_WIDTH - 500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
//			        v.show();
//			        
//			    }
		        /*
				if (tower == null) {
					Log.d("TowerWars", "s1Button");
					tower = TowerController.createTestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower);
				}
				*/
				
//			}
//			else if(pTouchArea == goodButton2 ){
//				/*if (tower1 == null) {
//					Log.d("TowerWars", "s2Button");
//					tower1 = TowerController.createTestTower(WorldView.MAP_WIDTH - 700 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
//					getEngine().getScene().getLastChild().attachChild(tower1);
//				}*/
//
//                if (timestamp != Math.round(System.currentTimeMillis() / 1000) ) {
//                    timestamp = Math.round(System.currentTimeMillis() / 1000);
//                    Dialog v = new BuildTowerView(this, WorldView.MAP_WIDTH - 700 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
//                    v.show();
//                    
//                   }			    
//			    
//			}
//			else if(pTouchArea == badButton1){
//				if (b1Tower == null) {
//					//b1Tower = TowerController.createTestTower(500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.44f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);//new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
//				}
//			}
//			else{
//				if (b2Tower == null) {
//					//b2Tower = TowerController.createTestTower(500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL); //new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
//				}
//			}
			
		return false;
		
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