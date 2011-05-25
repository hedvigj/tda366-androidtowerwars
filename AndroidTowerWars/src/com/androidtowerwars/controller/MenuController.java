package com.androidtowerwars.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import android.content.Intent;
import android.view.KeyEvent;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.view.MenuView;
import com.androidtowerwars.view.Preferences;
import com.androidtowerwars.view.WorldView;

public class MenuController implements IOnMenuItemClickListener {
    private GameActivity gameActivity;
    private MenuView menuView;

    public MenuController(GameActivity gameActivity, MenuView menuView) {
        this.gameActivity = gameActivity;
        this.menuView     = menuView;
    }

    public void loadScene() {
        MenuView.getMenuScene().setOnMenuItemClickListener(this);
    }

    public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, 
            final float pMenuItemLocalX, final float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
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
        if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
        	if(WorldView.getInstance().getScene().hasChildScene()) {
                        /* Remove the menu and reset it. */
                        menuView.close();
                } else {
                        /* Attach the menu. */
                        WorldView.getInstance().getScene().setChildScene(menuView.getMenuScene(), false, true, true);
                }
                return true;
        }
        else if(pKeyCode == KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
        	if(WorldView.getInstance().getScene().hasChildScene()) {
                /* Remove the menu and reset it. */
                menuView.close();
                return true;
        	}
        	else {
        		return false;
        	}
        }
        else {
                return false;
        }
}
}