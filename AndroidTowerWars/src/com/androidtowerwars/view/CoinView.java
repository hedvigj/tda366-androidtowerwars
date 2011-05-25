package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class CoinView {
    private static Texture mCoinTexture;
    private static TextureRegion mCoinTextureRegion;

    public static void loadResources(GameActivity gameActivity) {
        mCoinTexture       = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mCoinTextureRegion = TextureRegionFactory.createFromAsset(
                mCoinTexture, gameActivity, "gfx/Coin.png", 0,0);
        WorldView.getInstance().getTextureManager().loadTexture(mCoinTexture);
    }
}