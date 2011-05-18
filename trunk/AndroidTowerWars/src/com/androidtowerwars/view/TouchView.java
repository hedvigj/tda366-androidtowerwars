package com.androidtowerwars.view;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import android.graphics.Color;
import android.graphics.Typeface;

import com.androidtowerwars.GameActivity;

public class TouchView {
	
	public static ClickButton goodBarrack;
	public static ClickButton badBarrack;
	public static ClickButton meleeSoldierButton;
	public static ClickButton rangerSoldierButton;
	public static ClickButton wizardSoldierButton;
	public static ClickButton gigantSoldierButton;
	public static ChangeableText goldText;
	
	private Texture mGoodBarrackTexture;
	private TextureRegion mGoodBarrackTextureRegion;
	private Texture mMeleeButtonTexture;
	private TextureRegion mMeleeButtonTextureRegion;
	private Texture mRangerButtonTexture;
	private TextureRegion mRangerButtonTextureRegion;
	private Texture mWizardButtonTexture;
	private TextureRegion mWizardButtonTextureRegion;
	private Texture mGigantButtonTexture;
	private TextureRegion mGigantButtonTextureRegion;
	private Texture mCoinTexture;
	private TextureRegion mCoinTextureRegion;
	private Sprite coin;
	
	private GameActivity gameActivity;
	private HUD headUpDisplay;
	
	private Font mFont;
	private Texture mFontTexture;
	
	public TouchView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
			
		}
	
	public void loadResources() {
		mGoodBarrackTexture = new Texture(512,256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mGoodBarrackTextureRegion = TextureRegionFactory.createFromAsset(
				mGoodBarrackTexture, gameActivity,"gfx/Good_Barracks.png", 0,0);
		
		
		this.mCoinTexture = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mCoinTextureRegion = TextureRegionFactory.createFromAsset(
				mCoinTexture, gameActivity, "gfx/Coin.png", 0,0);
		
		mMeleeButtonTexture = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mMeleeButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mMeleeButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);
		
		mRangerButtonTexture = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRangerButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mRangerButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);
		
		mWizardButtonTexture = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mWizardButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mWizardButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);
		
		mGigantButtonTexture = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mGigantButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mGigantButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);
		
		this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);
       
        WorldView.getInstance().getTextureManager().loadTexture(this.mFontTexture);
        WorldView.getInstance().getFontManager().loadFont(this.mFont);
        WorldView.getInstance().getTextureManager().loadTexture(this.mGoodBarrackTexture);
        WorldView.getInstance().getTextureManager().loadTexture(this.mMeleeButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(this.mRangerButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(this.mWizardButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(this.mGigantButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(this.mCoinTexture);
	}

	public void loadScene(Scene scene){
		this.headUpDisplay = new HUD();
        goodBarrack = new ClickButton(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        badBarrack = new ClickButton(100, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        
        meleeSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-385,WorldView.CAMERA_HEIGHT-90 , mMeleeButtonTextureRegion);
        rangerSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-290,WorldView.CAMERA_HEIGHT-90 , mRangerButtonTextureRegion);
        wizardSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-195,WorldView.CAMERA_HEIGHT-90 , mWizardButtonTextureRegion);
        gigantSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-100,WorldView.CAMERA_HEIGHT-90 , mGigantButtonTextureRegion);
        coin = new Sprite(WorldView.CAMERA_WIDTH-175, 10, mCoinTextureRegion);
        goldText = new ChangeableText(WorldView.CAMERA_WIDTH-225, 10, this.mFont, "", "XXXXX".length());
        
        headUpDisplay.registerTouchArea(meleeSoldierButton);
        headUpDisplay.registerTouchArea(rangerSoldierButton);
        headUpDisplay.registerTouchArea(wizardSoldierButton);
        headUpDisplay.registerTouchArea(gigantSoldierButton);
        scene.registerTouchArea(goodBarrack);
        //scene.registerTouchArea(badBarrack);
        scene.getLastChild().attachChild(goodBarrack);
        scene.getLastChild().attachChild(badBarrack);
        headUpDisplay.getLastChild().attachChild(meleeSoldierButton);
        headUpDisplay.getLastChild().attachChild(rangerSoldierButton);
        headUpDisplay.getLastChild().attachChild(wizardSoldierButton);
        headUpDisplay.getLastChild().attachChild(gigantSoldierButton);
        headUpDisplay.getLastChild().attachChild(coin);
        headUpDisplay.getLastChild().attachChild(goldText);
        
        WorldView.getInstance().getCamera().setHUD(this.headUpDisplay);
	}
}
