package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class SoldierView {

	public static Texture mSkeletonTexture;
	public static TextureRegion mSkeletonTextureRegion;
	private GameActivity gameActivity;
	
	public SoldierView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
		
	}
	
	public void loadResources(){
		mSkeletonTexture = new Texture(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mSkeletonTextureRegion = TextureRegionFactory.createFromAsset(
				mSkeletonTexture, gameActivity, "gfx/skeleton_master_wizard.png",
				0, 0);
		WorldView.getInstance().getTextureManager().loadTexture(mSkeletonTexture);
	}
	
}
