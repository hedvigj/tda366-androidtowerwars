package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class CoinView {
	
	public static Texture mCoinTexture;
	public static TextureRegion mCoinTextureRegion;
	private GameActivity gameActivity;
	
	public CoinView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
	}
	
	public void loadResources(){
		
		mCoinTexture = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mCoinTextureRegion = TextureRegionFactory.createFromAsset(
				mCoinTexture, gameActivity, "gfx/Coin.png", 0,0);
		
		WorldView.getInstance().getTextureManager().loadTexture(this.mCoinTexture);
	}

}
