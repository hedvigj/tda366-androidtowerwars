package com.androidtowerwars.controller;

import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.input.touch.TouchEvent;

import android.app.Dialog;
import android.view.MotionEvent;

import com.androidtowerwars.BuildTowerView;
import com.androidtowerwars.GameActivity;
import com.androidtowerwars.UpgradeTowerView;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.view.ClickButton;
import com.androidtowerwars.view.TouchView;
import com.androidtowerwars.view.TowerTileView;
import com.androidtowerwars.view.WorldView;

public class TouchController implements IOnAreaTouchListener {
    private GameActivity gameActivity;

    public TouchController(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
            ITouchArea pTouchArea, float pTouchAreaLocalX,
            float pTouchAreaLocalY) {
        TowerTile towerTile;
        switch (pSceneTouchEvent.getAction()) {
        case MotionEvent.ACTION_DOWN:
            if(pTouchArea == TouchView.meleeSoldierButton){
                TouchView.meleeSoldierButton.touch();
            } else if(pTouchArea == TouchView.rangerSoldierButton){
                TouchView.rangerSoldierButton.touch();
            }
            towerTile = TowerTileView.towerTileSpriteMap.get(pTouchArea);
            if (towerTile != null && towerTile.isOccupied() != true) {
                ClickButton tileSprite = (ClickButton) pTouchArea;
                tileSprite.touch();
            } else if (towerTile != null && towerTile.isOccupied() == true) {
                Dialog v = new UpgradeTowerView(gameActivity, towerTile);
                v.show();
            }
            return false;
        case MotionEvent.ACTION_UP:
            if (pTouchArea == TouchView.meleeSoldierButton) {
                if (TouchView.meleeSoldierButton.releaseTouchSuccessfull()) {
                    SoldierController.createSprite(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.66f-32, Team.GOOD);            				
                }                		
            } else if (pTouchArea == TouchView.rangerSoldierButton) {
                if (TouchView.rangerSoldierButton.releaseTouchSuccessfull()) {
                    SoldierController.createAnimatedSprite(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.66f-64, Team.GOOD);
                }
            }
            towerTile = TowerTileView.towerTileSpriteMap.get(pTouchArea);
            if (towerTile != null && towerTile.isOccupied() != true) {
                ClickButton tileSprite = (ClickButton) pTouchArea;
                if (tileSprite.releaseTouchSuccessfull()) {
                    Dialog v = new BuildTowerView(gameActivity, towerTile);
                    v.show();
                }
            }
            return false;
        }
        return false;
    }
}