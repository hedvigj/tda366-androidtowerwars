package com.androidtowerwars.view;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import android.graphics.Color;
import android.graphics.Typeface;

import com.androidtowerwars.GameActivity;

public class TouchView {
    public static ClickButton goodBarrack;
    public static ClickButton badBarrack;
    public static ClickButton meleeSoldierButton;
    public static ClickButton rangerSoldierButton;
    public static ClickButton wizardSoldierButton;
    public static ClickButton gigantSoldierButton;

    public static ChangeableText goldText;
    public static ChangeableText gameOverText;
    public static ChangeableText goodWallHealthText;
    public static ChangeableText badWallHealthText;

    private static Texture mGoodBarrackTexture;
    private static TextureRegion mGoodBarrackTextureRegion;
    private static Texture mBadBarrackTexture;
    private static TextureRegion mBadBarrackTextureRegion;
    private static Texture mMeleeButtonTexture;
    private static TextureRegion mMeleeButtonTextureRegion;
    private static Texture mRangerButtonTexture;
    private static TextureRegion mRangerButtonTextureRegion;
    private static Texture mWizardButtonTexture;
    private static TextureRegion mWizardButtonTextureRegion;
    private static Texture mGigantButtonTexture;
    private static TextureRegion mGigantButtonTextureRegion;
    private static Texture mCoinTexture;
    private static TextureRegion mCoinTextureRegion;
    private static Texture mGoodWallHealthTexture;
    private static TextureRegion mGoodWallHealthTextureRegion;
    private static Texture mBadWallHealthTexture;
    private static TextureRegion mBadWallHealthTextureRegion;

    private static Sprite coin;
    private static Sprite goodWallHealth;
    private static Sprite badWallHealth;

    private static HUD headUpDisplay;

    private static Font mGoldFont;
    private static Font mGameOverFont;
    private static Texture mGoldFontTexture;
    private static Texture mGameOverFontTexture;

    public static void loadResources(GameActivity gameActivity) {
        mGoodBarrackTexture       = new Texture(512,256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mGoodBarrackTextureRegion = TextureRegionFactory.createFromAsset(
                mGoodBarrackTexture, gameActivity,"gfx/Good_Barracks.png", 0,0);

        mBadBarrackTexture        = new Texture(512,256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mBadBarrackTextureRegion  = TextureRegionFactory.createFromAsset(
                mBadBarrackTexture, gameActivity,"gfx/Bad_Barracks.png", 0,0);

        mCoinTexture       = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mCoinTextureRegion = TextureRegionFactory.createFromAsset(
                mCoinTexture, gameActivity, "gfx/Coin.png", 0,0);

        mGoodWallHealthTexture       = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mGoodWallHealthTextureRegion = TextureRegionFactory.createFromAsset(
                mGoodWallHealthTexture, gameActivity, "gfx/GoodWallHealth.png", 0,0);

        mBadWallHealthTexture       = new Texture(32,32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mBadWallHealthTextureRegion = TextureRegionFactory.createFromAsset(
                mBadWallHealthTexture, gameActivity, "gfx/BadWallHealth.png", 0,0);

        mMeleeButtonTexture       = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mMeleeButtonTextureRegion = TextureRegionFactory.createFromAsset(
                mMeleeButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);

        mRangerButtonTexture       = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mRangerButtonTextureRegion = TextureRegionFactory.createFromAsset(
                mRangerButtonTexture, gameActivity, "gfx/cyclopButton.png", 0,0);

        mWizardButtonTexture       = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mWizardButtonTextureRegion = TextureRegionFactory.createFromAsset(
                mWizardButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);

        mGigantButtonTexture       = new Texture(128,128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mGigantButtonTextureRegion = TextureRegionFactory.createFromAsset(
                mGigantButtonTexture, gameActivity, "gfx/skellyButton.png", 0,0);

        mGoldFontTexture     = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mGameOverFontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mGoldFont            = new Font(mGoldFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);

        mGameOverFont = new Font(mGameOverFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 64, true, Color.DKGRAY);

        WorldView.getInstance().getTextureManager().loadTexture(mGoldFontTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mGameOverFontTexture);
        WorldView.getInstance().getFontManager().loadFont(mGoldFont);
        WorldView.getInstance().getFontManager().loadFont(mGameOverFont);
        WorldView.getInstance().getTextureManager().loadTexture(mGoodBarrackTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mBadBarrackTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mMeleeButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mRangerButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mWizardButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mGigantButtonTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mCoinTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mGoodWallHealthTexture);
        WorldView.getInstance().getTextureManager().loadTexture(mBadWallHealthTexture);
    }

    public static void loadScene(Scene scene) {
        headUpDisplay = new HUD();
        goodBarrack   = new ClickButton(WorldView.MAP_WIDTH-280, WorldView.MAP_HEIGHT*0.40f, mGoodBarrackTextureRegion);
        badBarrack    = new ClickButton(-20, WorldView.MAP_HEIGHT*0.40f, mBadBarrackTextureRegion);

        meleeSoldierButton  = new ClickButton(WorldView.CAMERA_WIDTH-385,WorldView.CAMERA_HEIGHT-90 , mMeleeButtonTextureRegion);
        rangerSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-290,WorldView.CAMERA_HEIGHT-90 , mRangerButtonTextureRegion);
        wizardSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-195,WorldView.CAMERA_HEIGHT-90 , mWizardButtonTextureRegion);
        gigantSoldierButton = new ClickButton(WorldView.CAMERA_WIDTH-100,WorldView.CAMERA_HEIGHT-90 , mGigantButtonTextureRegion);
        coin                = new Sprite(WorldView.CAMERA_WIDTH-225, 10, mCoinTextureRegion);
        goldText            = new ChangeableText(WorldView.CAMERA_WIDTH-300, 10, mGoldFont, "", "XXXXX".length());
        goodWallHealth      = new Sprite(WorldView.CAMERA_WIDTH-75, 10, mGoodWallHealthTextureRegion);
        badWallHealth       = new Sprite(43, 10, mBadWallHealthTextureRegion);
        gameOverText        = new ChangeableText(WorldView.CAMERA_WIDTH/2-2*64, WorldView.CAMERA_HEIGHT / 2 - 64, mGameOverFont, "", "XXXXXXXXX".length());
        badWallHealthText   = new ChangeableText(85, 10, mGoldFont, "","XXXXX".length() );
        goodWallHealthText  = new ChangeableText(WorldView.CAMERA_WIDTH-140, 10, mGoldFont, "","XXXXX".length() );

        headUpDisplay.registerTouchArea(meleeSoldierButton);
        headUpDisplay.registerTouchArea(rangerSoldierButton);
        headUpDisplay.registerTouchArea(wizardSoldierButton);
        headUpDisplay.registerTouchArea(gigantSoldierButton);

        scene.registerTouchArea(goodBarrack);
        scene.getLastChild().attachChild(goodBarrack);
        scene.getLastChild().attachChild(badBarrack);

        headUpDisplay.getLastChild().attachChild(meleeSoldierButton);
        headUpDisplay.getLastChild().attachChild(rangerSoldierButton);
        headUpDisplay.getLastChild().attachChild(wizardSoldierButton);
        headUpDisplay.getLastChild().attachChild(gigantSoldierButton);
        headUpDisplay.getLastChild().attachChild(coin);
        headUpDisplay.getLastChild().attachChild(goldText);
        headUpDisplay.getLastChild().attachChild(goodWallHealth);
        headUpDisplay.getLastChild().attachChild(badWallHealth);
        headUpDisplay.getLastChild().attachChild(goodWallHealthText);
        headUpDisplay.getLastChild().attachChild(badWallHealthText);

        WorldView.getInstance().getCamera().setHUD(headUpDisplay);
    }

    public static void gameOver(boolean victory) {
        if (victory) {
            gameOverText.setText("Victory!");
            System.out.println("Victory!");
        } else {
            gameOverText.setText("Game Over!");
        }
        headUpDisplay.getLastChild().attachChild(gameOverText);
    }
}