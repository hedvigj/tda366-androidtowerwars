package com.androidtowerwars.view;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;

public class BackgroundView {
    private static Texture mBackground1Texture;
    private static Texture mBackground2Texture;
    private static Texture mBackground3Texture;
    private static Texture mBackground4Texture;
    private static Texture mBackground5Texture;
    private static Texture mBackground6Texture;
    private static Texture mBackground7Texture;
    private static Texture mBackground8Texture;

    private static TextureRegion mBackground1TextureRegion;
    private static TextureRegion mBackground2TextureRegion;
    private static TextureRegion mBackground3TextureRegion;
    private static TextureRegion mBackground4TextureRegion;
    private static TextureRegion mBackground5TextureRegion;
    private static TextureRegion mBackground6TextureRegion;
    private static TextureRegion mBackground7TextureRegion;
    private static TextureRegion mBackground8TextureRegion;

    public static void loadResources(GameActivity gameActivity) {
        mBackground1Texture       = new Texture(512, 2048);
        mBackground1TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground1Texture, gameActivity,
                "gfx/Background1.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground1Texture);

        mBackground2Texture       = new Texture(512, 2048);
        mBackground2TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground2Texture, gameActivity,
                "gfx/Background2.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground2Texture);

        mBackground3Texture       = new Texture(512, 2048);
        mBackground3TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground3Texture, gameActivity,
                "gfx/Background3.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground3Texture);

        mBackground4Texture       = new Texture(512, 2048);
        mBackground4TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground4Texture, gameActivity,
                "gfx/Background4.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground4Texture);

        mBackground5Texture       = new Texture(512, 2048);
        mBackground5TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground5Texture, gameActivity,
                "gfx/Background5.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground5Texture);

        mBackground6Texture       = new Texture(512, 2048);
        mBackground6TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground6Texture, gameActivity,
                "gfx/Background6.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground6Texture);

        mBackground7Texture       = new Texture(512, 2048);
        mBackground7TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground7Texture, gameActivity,
                "gfx/Background7.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground7Texture);

        mBackground8Texture       = new Texture(512, 2048);
        mBackground8TextureRegion = TextureRegionFactory.createFromAsset(
                mBackground8Texture, gameActivity,
                "gfx/Background81.jpg", 0, 0);
        WorldView.getInstance().getTextureManager().loadTexture(mBackground8Texture);
    }

    public static void loadScene(Scene scene){
        final Sprite background1 = new Sprite(0, 0,
                BackgroundView.mBackground1TextureRegion);
        scene.getLastChild().attachChild(background1);
        final Sprite background2 = new Sprite(506, 0,
                BackgroundView.mBackground2TextureRegion);
        scene.getLastChild().attachChild(background2);
        final Sprite background3 = new Sprite(1012, 0,
                BackgroundView.mBackground3TextureRegion);
        scene.getLastChild().attachChild(background3);
        final Sprite background4 = new Sprite(1518, 0,
                BackgroundView.mBackground4TextureRegion);
        scene.getLastChild().attachChild(background4);
        final Sprite background5 = new Sprite(2024, 0,
                BackgroundView.mBackground5TextureRegion);
        scene.getLastChild().attachChild(background5);
        final Sprite background6 = new Sprite(2530, 0,
                BackgroundView.mBackground6TextureRegion);
        scene.getLastChild().attachChild(background6);
        final Sprite background7 = new Sprite(3036, 0,
                BackgroundView.mBackground7TextureRegion);
        scene.getLastChild().attachChild(background7);
        final Sprite background8 = new Sprite(3535, 0,
                BackgroundView.mBackground8TextureRegion);
        scene.getLastChild().attachChild(background8);
    }
}