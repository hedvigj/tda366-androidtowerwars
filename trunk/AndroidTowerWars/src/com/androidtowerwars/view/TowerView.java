package com.androidtowerwars.view;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;

public class TowerView {
	
	public static Texture mTowerTexture;
	public static TextureRegion mTowerTextureRegion;
	private GameActivity gameActivity;
	
	public TowerView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
	}
	
	public void loadResources(){
		mTowerTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mTowerTextureRegion = TextureRegionFactory.createFromAsset(
				mTowerTexture, gameActivity, "gfx/tower1.png",
				0, 0);
		WorldView.getInstance().getTextureManager().loadTexture(mTowerTexture);
	}
}
