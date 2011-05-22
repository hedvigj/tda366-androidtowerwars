package com.androidtowerwars.view;

import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ISoldier;

public class RangerView extends ObserverAnimatedSprite {
	public static Texture mCyclopTexture;
	public static TiledTextureRegion mCyclopTextureRegion;
	public static ConcurrentHashMap<ISoldier, RangerView> rangerSpriteMap = new ConcurrentHashMap<ISoldier, RangerView>();
	

	public RangerView(float pX, float pY, ISoldier ranger) {
		super(pX, pY,mCyclopTextureRegion);

	}

	public static void loadResources(GameActivity gameActivity) {
		mCyclopTexture = new Texture(256, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mCyclopTextureRegion = TextureRegionFactory.createTiledFromAsset(
				mCyclopTexture, gameActivity, "gfx/tiledLeftCyclop.png", 0, 0, 3, 1);
		WorldView.getInstance().getTextureManager().loadTexture(mCyclopTexture);

	}
}
