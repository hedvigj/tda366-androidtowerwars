package com.androidtowerwars;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
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


import android.util.Log;
import android.view.KeyEvent;

import com.androidtowerwars.constants.Constants;

public class Main extends BaseGameActivity implements IOnMenuItemClickListener, IOnAreaTouchListener{

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
	private Texture mButtonTexture;
	private TextureRegion mBackgroundTextureRegion;
	public TextureRegion mSkeletonTextureRegion;	
	private TextureRegion mTowerTextureRegion;
	public Controller controller;
	private TextureRegion mMenuResetTextureRegion; 
	private Texture mMenuResetTexture;
	private Texture mMenuQuitTexture;
	private TextureRegion mMenuQuitTextureRegion;
	private TextureRegion mButtonTextureRegion;
	protected MenuScene mMenuScene;
	private ClickButton s1Button;
	private ClickButton s2Button;
	private ClickButton b1Button;
	private ClickButton b2Button;
	private TestTower tower;
	private TestTower tower1;
	private BadTower b1Tower;
	private BadTower b2Tower;
	protected static final int MENU_RESET = 0;
    protected static final int MENU_QUIT = MENU_RESET + 1;

	
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
        this.mMenuResetTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuResetTexture, this, "gfx/menu_reset.png", 0, 0);
        this.mMenuQuitTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuQuitTexture, this, "gfx/menu_quit.png", 0, 0);
		
        this.mEngine.getTextureManager().loadTexture(this.mButtonTexture);
		this.mEngine.getTextureManager().loadTexture(this.mBackgroundTexture);
		this.mEngine.getTextureManager().loadTexture(this.mSkeletonTexture);
		this.mEngine.getTextureManager().loadTexture(this.mTowerTexture);
        this.mEngine.getTextureManager().loadTexture(this.mMenuResetTexture);
        this.mEngine.getTextureManager().loadTexture(this.mMenuQuitTexture);
      

	}

	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		this.createMenuScene();
		
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

		//final TestTower tower = new TestTower(Constants.MAP_WIDTH-300, Constants.MAP_HEIGHT * 0.5f, this.mTowerTextureRegion, 200);
		//final BadTower bTower = new BadTower(300, Constants.MAP_HEIGHT*0.5f, this.mTowerTextureRegion, 200);
		//scene.getLastChild().attachChild(tower);
		//scene.getLastChild().attachChild(bTower);
		controller.createSpriteSpawnTimeHandler();
		controller.createRightSpawnTimeHandler();
		
		scene.setOnAreaTouchListener(this);
        s1Button = new ClickButton(Constants.MAP_WIDTH - 400, Constants.MAP_HEIGHT * 0.4f , mButtonTextureRegion);
        s2Button = new ClickButton(Constants.MAP_WIDTH - 400, Constants.MAP_HEIGHT * 0.6f , mButtonTextureRegion);
        b1Button = new ClickButton(300, Constants.MAP_HEIGHT*0.4f, mButtonTextureRegion);
        b2Button = new ClickButton(300, Constants.MAP_HEIGHT*0.6f, mButtonTextureRegion);
        
        
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
                     default:
                             return false;
             }
     }

     // ===========================================================
     // Methods
     // ===========================================================

     protected void createMenuScene() {
             this.mMenuScene = new MenuScene(this.mCamera);

             final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESET, this.mMenuResetTextureRegion);
             resetMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
             this.mMenuScene.addMenuItem(resetMenuItem);

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
					tower = new TestTower(Constants.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, Constants.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200);
					getEngine().getScene().getLastChild().attachChild(tower);
				}
			}
			else if(pTouchArea == s2Button ){
				if (tower1 == null) {
					Log.d("TowerWars", "s2Button");
					tower1 = new TestTower(Constants.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, Constants.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200);
					getEngine().getScene().getLastChild().attachChild(tower1);
				}
			}
			else if(pTouchArea == b1Button){
				if (b1Tower == null) {
					b1Tower = new BadTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, Constants.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200);
					getEngine().getScene().getLastChild().attachChild(b1Tower);
				}
			}
			else{
				if (b2Tower == null) {
					b2Tower = new BadTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, Constants.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200);
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