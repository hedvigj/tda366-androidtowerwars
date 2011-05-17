package com.androidtowerwars.view;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class TouchView {
	
	public static ClickButton goodBarrack;
	public static ClickButton badBarrack;
	private Texture mGoodBarrackTexture;
	private TextureRegion mGoodBarrackTextureRegion;
	private GameActivity gameActivity;
	
	public TouchView(GameActivity gameActivity){
		this.gameActivity = gameActivity;
			
		}
	
	public void loadResources() {
		mGoodBarrackTexture = new Texture(512,256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mGoodBarrackTextureRegion = TextureRegionFactory.createFromAsset(
				mGoodBarrackTexture, gameActivity,"gfx/Good_Barracks.png", 0,0);
		WorldView.getInstance().getTextureManager().loadTexture(this.mGoodBarrackTexture);
	}

	public void loadScene(Scene scene){
        goodBarrack = new ClickButton(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        badBarrack = new ClickButton(100, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        scene.registerTouchArea(goodBarrack);
        scene.registerTouchArea(badBarrack);
        scene.getLastChild().attachChild(goodBarrack);
        scene.getLastChild().attachChild(badBarrack);
	}
}
