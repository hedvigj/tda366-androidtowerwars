package com.androidtowerwars.view;

import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.IProjectile;
import com.androidtowerwars.model.ISoldier;

public class ProjectileView extends ObserverSprite {
	
	public static Texture mArrowTexture;
	public static TextureRegion mArrowTextureRegion;
	public static ConcurrentHashMap<IProjectile, ProjectileView> projectileSpriteMap = new ConcurrentHashMap<IProjectile, ProjectileView>();
	
	
	public ProjectileView(float pX, float pY){
		super(pX,pY,mArrowTextureRegion);
	}
	
	public static void loadResources(GameActivity gameActivity){
		mArrowTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mArrowTextureRegion = TextureRegionFactory.createFromAsset(
				mArrowTexture, gameActivity, "gfx/Arrow2.png",
				0, 0);
		WorldView.getInstance().getTextureManager().loadTexture(mArrowTexture);
	}

}
