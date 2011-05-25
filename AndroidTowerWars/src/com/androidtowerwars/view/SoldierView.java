package com.androidtowerwars.view;

import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.ISoldier;

public class SoldierView extends ObserverSprite {
    public static Texture mSkeletonTexture;
    public static TextureRegion mSkeletonTextureRegion;
    public static ConcurrentHashMap<ISoldier, SoldierView> soldierSpriteMap = new ConcurrentHashMap<ISoldier, SoldierView>();

    public SoldierView(float pX, float pY, ISoldier soldier) {
        super(pX, pY, mSkeletonTextureRegion);
    }

    public static void loadResources(GameActivity gameActivity) {
        mSkeletonTexture       = new Texture(64, 64,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mSkeletonTextureRegion = TextureRegionFactory.createFromAsset(
                mSkeletonTexture, gameActivity, "gfx/skeleton_master_wizard.png",
                0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mSkeletonTexture);
    }
}