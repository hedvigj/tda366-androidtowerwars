package com.androidtowerwars.view;

import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ITower;


public class TowerView {

	public static Texture mTowerTexture;
	public static TextureRegion mTowerTextureRegion;
	public static Texture mMagmaPitTowerTexture;
	public static TextureRegion mMagmaPitTowerTextureRegion;
	
	public static ConcurrentHashMap<ITower, Sprite> towerSpriteMap = new ConcurrentHashMap<ITower, Sprite>();
	
	public static void loadResources(GameActivity gameActivity) {
		mTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				mTowerTexture, gameActivity, "gfx/tower1.png", 0, 0);
		
		mMagmaPitTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mMagmaPitTowerTextureRegion = TextureRegionFactory.createFromAsset(
				mMagmaPitTowerTexture, gameActivity, "gfx/MagmaPitTower.png", 0, 0);
		
		WorldView.getInstance().getTextureManager().loadTexture(mTowerTexture);
		WorldView.getInstance().getTextureManager().loadTexture(mMagmaPitTowerTexture);
	}
}
