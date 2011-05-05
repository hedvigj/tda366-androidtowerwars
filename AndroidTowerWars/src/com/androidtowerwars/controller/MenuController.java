package com.androidtowerwars.controller;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.sprite.Sprite;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.Preferences;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.WorldView;

public class MenuController implements IOnMenuItemClickListener {
	

    
    private final Scene scene = WorldView.getInstance().getScene();
    private GameActivity gameActivity;
    private MenuView menuView;
	
	public MenuController(GameActivity gameActivity, MenuView menuView){
		this.gameActivity = gameActivity;
		this.menuView = menuView;
	}

    public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
//                case MENU_RESET:
//                        /* Restart the animation. */
//            		runOnUpdateThread(new Runnable() {
//          			 public void run() {
//          			 /* Now it is save to remove the entity! */
//          			.getLastChild().detachChild(b2Tower);
//          			WorldView.getInstance().getScene().getLastChild().detachChild(b1Tower);
//          			WorldView.getInstance().getScene().getLastChild().detachChild(tower);
//          			WorldView.getInstance().getScene().getLastChild().detachChild(tower1);
//               	 	tower = null;
//               	 	tower1 = null;
//               	 	b1Tower = null;
//               	 	b2Tower = null; 
//          			 }
//          			 });
//               	 	
//               	 	
//                        /* Remove the menu and reset it. */
//               	 	getEngine().getScene().clearChildScene();
//               	 	getEngine().getScene().reset();
//             
//                        return true;
                case MenuView.MENU_QUIT:
                        /* End Activity. */
                        gameActivity.finish();
                        return true;
                case MenuView.MENU_SETTINGS:
        			gameActivity.startActivity(new Intent(gameActivity, Preferences.class));
       			return true;
                default:
                        return false;
        }
        
}
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
    	Log.d("TowerWars", "Åt helvete");
        if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {    
        	if(WorldView.getInstance().getScene().hasChildScene()) {
                        /* Remove the menu and reset it. */
                        menuView.close();
                } else {
                        /* Attach the menu. */
                        WorldView.getInstance().getScene().setChildScene(menuView.getMenuScene(), false, true, true);
                }
                return true;
        } else {
                return false;
        }
}
}
