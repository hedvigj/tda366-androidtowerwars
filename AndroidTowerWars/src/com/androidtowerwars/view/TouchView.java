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
	public static ChangeableText goldText;
	private Texture mGoodBarrackTexture;
	private TextureRegion mGoodBarrackTextureRegion;
	private GameActivity gameActivity;
	private HUD headUpDisplay;
	private Texture mCoinTexture;
	private TextureRegion mCoinTextureRegion;
	private Sprite coin;
	
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
		WorldView.getInstance().getTextureManager().loadTexture(this.mGoodBarrackTexture);
		
		this.mCoinTexture = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mCoinTextureRegion = TextureRegionFactory.createFromAsset(
				mCoinTexture, gameActivity, "gfx/Coin.png", 0,0);
		
		this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);
        WorldView.getInstance().getTextureManager().loadTexture(this.mFontTexture);
        WorldView.getInstance().getFontManager().loadFont(this.mFont);
        WorldView.getInstance().getTextureManager().loadTexture(this.mCoinTexture);
	}

	public void loadScene(Scene scene){
		this.headUpDisplay = new HUD();
        goodBarrack = new ClickButton(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        badBarrack = new ClickButton(100, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        coin = new Sprite(WorldView.CAMERA_WIDTH-175, 10, mCoinTextureRegion);
        goldText = new ChangeableText(WorldView.CAMERA_WIDTH-225, 10, this.mFont, "", "XXXXX".length());
        
        scene.registerTouchArea(goodBarrack);
        //scene.registerTouchArea(badBarrack);
        scene.getLastChild().attachChild(goodBarrack);
        scene.getLastChild().attachChild(badBarrack);
        headUpDisplay.getLastChild().attachChild(coin);
        headUpDisplay.getLastChild().attachChild(goldText);
        
        WorldView.getInstance().getCamera().setHUD(this.headUpDisplay);
	}
}
