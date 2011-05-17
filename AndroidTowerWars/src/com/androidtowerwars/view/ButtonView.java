package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class ButtonView {

	public static TextureRegion mButtonTextureRegion;
	public static Texture mButtonTexture;
	private GameActivity gameActivity;
	
	public ButtonView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
	}
	
	public void loadResources(){
		
		mButtonTexture = new Texture(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    
		mButtonTextureRegion = TextureRegionFactory.createFromAsset(
				mButtonTexture, gameActivity, "gfx/GroundTile.png", 0,0);
        WorldView.getInstance().getTextureManager().loadTexture(mButtonTexture);
	}

	
	
}
