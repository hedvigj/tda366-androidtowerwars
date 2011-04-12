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

import com.androidtowerwars.controller.SoldierController;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.model.Castle;
import com.androidtowerwars.model.TestTower;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.World.Team;

public class GameActivity extends BaseGameActivity implements IOnMenuItemClickListener, IOnAreaTouchListener{

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
	private Texture mCastleTexture;
	private TextureRegion mBackgroundTextureRegion;
	public TextureRegion mSkeletonTextureRegion;	
	private TextureRegion mTowerTextureRegion;
	private TextureRegion mCastleTextureRegion;
	public SoldierController soldierController;
	public TowerController towerController;
	
	private TextureRegion mMenuResetTextureRegion; 
	private Texture mMenuResetTexture;
	private Texture mMenuQuitTexture;
	private Texture mMenuSettingsTexture;
	private TextureRegion mMenuQuitTextureRegion;
	private TextureRegion mButtonTextureRegion;
	private TextureRegion mMenuSettingsTextureRegion;
	protected MenuScene mMenuScene;
	private ClickButton s1Button;
	private ClickButton s2Button;
	private ClickButton b1Button;
	private ClickButton b2Button;
	private Sprite tower;
	private Sprite tower1;
	private Sprite b1Tower;
	private Sprite b2Tower;
	protected static final int MENU_RESET = 0;
    protected static final int MENU_QUIT = MENU_RESET + 2;
    protected static final int MENU_SETTINGS = 1;


	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	public Engine onLoadEngine() {
		instance = this;
		Engine engine = new World();
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
		this.mTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				this.mTowerTexture, this, "gfx/tower1.png",
				0, 0);
		this.mButtonTexture = new Texture(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mButtonTextureRegion = TextureRegionFactory.createFromAsset(
				this.mButtonTexture, this, "gfx/GroundTile.png", 0,0);
		
		
		this.mMenuResetTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mMenuQuitTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mMenuSettingsTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mMenuResetTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuResetTexture, this, "gfx/menu_reset.png", 0, 0);
        this.mMenuQuitTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuQuitTexture, this, "gfx/menu_quit.png", 0, 0);
        this.mMenuSettingsTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuSettingsTexture, this, "gfx/menu_quit.png", 0, 0);
		
        this.mEngine.getTextureManager().loadTexture(this.mButtonTexture);
		getEngine().getTextureManager().loadTexture(this.mBackgroundTexture);
		getEngine().getTextureManager().loadTexture(this.mSkeletonTexture);
		getEngine().getTextureManager().loadTexture(this.mTowerTexture);
        getEngine().getTextureManager().loadTexture(this.mMenuResetTexture);
        getEngine().getTextureManager().loadTexture(this.mMenuQuitTexture);
        getEngine().getTextureManager().loadTexture(this.mMenuSettingsTexture);
      

	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		soldierController = new SoldierController();
		scene.getLastChild().attachChild(soldierController);
		towerController = TowerController.getInstance();
		scene.getLastChild().attachChild(towerController);
		getEngine().registerUpdateHandler(new FPSLogger());
		
		
		this.createMenuScene();
		
		/* Limit scene size */
		World.getInstance().getCamera().setBounds(0, World.MAP_WIDTH, 0, World.MAP_HEIGHT);
		World.getInstance().getCamera().setBoundsEnabled(true);
		/* Center camera */
		World.getInstance().getCamera().setCenter(World.MAP_WIDTH * 0.5f,
				World.MAP_HEIGHT * 0.5f);

		Scene.IOnSceneTouchListener touchListener = new DragAndZoomController(
				World.getInstance().getCamera());

		scene.setOnSceneTouchListener(touchListener);
		final Sprite background = new Sprite(0, 0,
				this.mBackgroundTextureRegion);
		scene.getLastChild().attachChild(background);

		soldierController.createSpriteSpawnTimeHandler();
		soldierController.createRightSpawnTimeHandler();
		
		scene.setOnAreaTouchListener(this);
        s1Button = new ClickButton(World.MAP_WIDTH - 400, World.MAP_HEIGHT * 0.4f , mButtonTextureRegion);
        s2Button = new ClickButton(World.MAP_WIDTH - 400, World.MAP_HEIGHT * 0.6f , mButtonTextureRegion);
        b1Button = new ClickButton(300, World.MAP_HEIGHT*0.4f, mButtonTextureRegion);
        b2Button = new ClickButton(300, World.MAP_HEIGHT*0.6f, mButtonTextureRegion);

        
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
	
	 @Override
     public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
             if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
                     if(getEngine().getScene().hasChildScene()) {
                             /* Remove the menu and reset it. */
                             this.mMenuScene.back();
                     } else {
                             /* Attach the menu. */
                             getEngine().getScene().setChildScene(this.mMenuScene, false, true, true);
                     }
                     return true;
             } else {
                     return super.onKeyDown(pKeyCode, pEvent);
             }
     }

     
     public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
             switch(pMenuItem.getID()) {
                     case MENU_RESET:
                             /* Restart the animation. */
                 		runOnUpdateThread(new Runnable() {
               			 public void run() {
               			 /* Now it is save to remove the entity! */
               				getEngine().getScene().getLastChild().detachChild(b2Tower);
                    	 	getEngine().getScene().getLastChild().detachChild(b1Tower);
                    	 	getEngine().getScene().getLastChild().detachChild(tower);
                    	 	getEngine().getScene().getLastChild().detachChild(tower1);
                    	 	tower = null;
                    	 	tower1 = null;
                    	 	b1Tower = null;
                    	 	b2Tower = null; 
               			 }
               			 });
                    	 	
                    	 	
                             /* Remove the menu and reset it. */
                    	 	getEngine().getScene().clearChildScene();
                    	 	getEngine().getScene().reset();
                  
                             return true;
                     case MENU_QUIT:
                             /* End Activity. */
                             this.finish();
                             return true;
                     case MENU_SETTINGS:
             			startActivity(new Intent(this, Preferences.class));
            			return true;
                     default:
                             return false;
             }
     }

     // ===========================================================
     // Methods
     // ===========================================================

     protected void createMenuScene() {
             this.mMenuScene = new MenuScene(World.getInstance().getCamera());

             final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESET, this.mMenuResetTextureRegion);
             resetMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
             this.mMenuScene.addMenuItem(resetMenuItem);
             // settingsbutton - says quit atm
             final SpriteMenuItem settingsMenuItem = new SpriteMenuItem(MENU_SETTINGS, this.mMenuSettingsTextureRegion);
             settingsMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
             this.mMenuScene.addMenuItem(settingsMenuItem);

             final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, this.mMenuQuitTextureRegion);
             quitMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
             this.mMenuScene.addMenuItem(quitMenuItem);


             
             this.mMenuScene.buildAnimations();

             this.mMenuScene.setBackgroundEnabled(false);

             this.mMenuScene.setOnMenuItemClickListener(this);
     }

	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
			
			if(pTouchArea == s1Button){
				if (tower == null) {
					Log.d("TowerWars", "s1Button");
					tower = TowerController.createTestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower);
				}
			}
			else if(pTouchArea == s2Button ){
				if (tower1 == null) {
					Log.d("TowerWars", "s2Button");
					tower1 = TowerController.createTestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower1);
				}
			}
			else if(pTouchArea == b1Button){
				if (b1Tower == null) {
					b1Tower = TowerController.createTestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);//new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
					getEngine().getScene().getLastChild().attachChild(b1Tower);
				}
			}
			else{
				if (b2Tower == null) {
					b2Tower = TowerController.createTestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL); //new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
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