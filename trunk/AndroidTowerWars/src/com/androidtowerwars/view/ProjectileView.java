package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class ProjectileView {
	
	public static Texture mArrowTexture;
	public static TextureRegion mArrowTextureRegion;
	private GameActivity gameActivity;
	
	public ProjectileView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
	}
	
	public void loadResources(){
		mArrowTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mArrowTextureRegion = TextureRegionFactory.createFromAsset(
				mArrowTexture, gameActivity, "gfx/Arrow2.png",
				0, 0);
		WorldView.getInstance().getTextureManager().loadTexture(mArrowTexture);
	}

}
