package com.androidtowerwars.view;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class BackgroundView {
	
	private Texture mBackgroundTexture;
	public static TextureRegion mBackgroundTextureRegion;
	private GameActivity gameActivity;
	
	public BackgroundView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
	}
	
	public void loadResources(){
		mBackgroundTexture = new Texture(2048, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mBackgroundTextureRegion = TextureRegionFactory.createFromAsset(
				mBackgroundTexture, gameActivity,
				"gfx/parallax_background_layer_back.png", 0, 0);
		WorldView.getInstance().getTextureManager().loadTexture(mBackgroundTexture);
	}

	public void loadScene(Scene scene){
		
		final Sprite background = new Sprite(0, 0,
				BackgroundView.mBackgroundTextureRegion);
		scene.getLastChild().attachChild(background);
		
	}
	

}
