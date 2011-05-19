package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;


public class TowerView {

	public static Texture mTowerTexture;
	public static TextureRegion mTowerTextureRegion;
	
	public static void loadResources(GameActivity gameActivity) {
		mTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				mTowerTexture, gameActivity, "gfx/tower1.png", 0, 0);
		WorldView.getInstance().getTextureManager().loadTexture(mTowerTexture);
	}
}
