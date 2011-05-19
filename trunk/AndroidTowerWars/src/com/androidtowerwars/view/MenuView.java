package com.androidtowerwars.view;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.controller.MenuController;

public class MenuView {
	
	//private TextureRegion mResetTextureRegion; 
	//private Texture mResetTexture;
	private Texture mQuitTexture;
	private Texture mSettingsTexture;
	private TextureRegion mQuitTextureRegion;
	private TextureRegion mSettingsTextureRegion;
	protected static MenuScene mScene;
	private GameActivity gameActivity;
	
	public static final int MENU_RESET = 0;
    public static final int MENU_QUIT = MENU_RESET + 2;
    public static final int MENU_SETTINGS = 1;
	
	public MenuView(GameActivity gameActivity){
	this.gameActivity = gameActivity;
		
	}
	
	public void close() {
		mScene.back();
	}
	
	public void loadResources() {		
		//mResetTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mQuitTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mSettingsTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        //mResetTextureRegion = TextureRegionFactory.createFromAsset(mResetTexture, gameActivity, "gfx/menu_reset.png", 0, 0);
        mQuitTextureRegion = TextureRegionFactory.createFromAsset(mQuitTexture, gameActivity, "gfx/menu_quit.png", 0, 0);
        mSettingsTextureRegion = TextureRegionFactory.createFromAsset(mSettingsTexture, gameActivity, "gfx/menu_settings.png", 0, 0);
        
        final TextureManager textureManager = WorldView.getInstance().getTextureManager();
        //textureManager.loadTexture(mResetTexture);
        textureManager.loadTexture(mQuitTexture);
        textureManager.loadTexture(mSettingsTexture);
	}
	
	private void addMenuItem(MenuScene menuScene, TextureRegion textureRegion, int menuItem) {
		
		SpriteMenuItem spriteMenuItem = new SpriteMenuItem(menuItem, textureRegion);
		spriteMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(spriteMenuItem);
	}
	
    public void createMenuScene() {
    	mScene = new MenuScene(WorldView.getInstance().getCamera());
    	
    	addMenuItem(mScene, mSettingsTextureRegion, MENU_SETTINGS);
    	addMenuItem(mScene, mQuitTextureRegion, MENU_QUIT);
    	
        mScene.buildAnimations();
        mScene.setBackgroundEnabled(false);
      
}
    
    public static MenuScene getMenuScene() {
    	return mScene;
    }

}
