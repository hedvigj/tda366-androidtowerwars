package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class ButtonView {
    public static TextureRegion mButtonTextureRegion;
    private static Texture mButtonTexture;

    public static void loadResources(GameActivity  gameActivity) {
        mButtonTexture       = new Texture(256, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mButtonTextureRegion = TextureRegionFactory.createFromAsset(
                mButtonTexture, gameActivity, "gfx/GroundTile.png", 0,0);
        WorldView.getInstance().getTextureManager().loadTexture(mButtonTexture);
    }
}