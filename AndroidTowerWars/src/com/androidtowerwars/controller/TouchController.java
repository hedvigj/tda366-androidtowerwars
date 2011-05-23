package com.androidtowerwars.controller;

import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.input.touch.TouchEvent;

import android.app.Dialog;
import android.view.MotionEvent;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.BuildTowerView;
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
		switch(pSceneTouchEvent.getAction())
        {
                case MotionEvent.ACTION_DOWN:
                	if(pTouchArea == TouchView.meleeSoldierButton){
                		TouchView.meleeSoldierButton.touch();
            		}
            		else if(pTouchArea == TouchView.rangerSoldierButton){
            			TouchView.rangerSoldierButton.touch();
            		}
                    
                	towerTile = TowerTileView.towerTileSpriteMap.get(pTouchArea);
            		if (towerTile != null && towerTile.isOccupied() != true) {
            		    ClickButton tileSprite = (ClickButton) pTouchArea;
            		    tileSprite.touch();
            		}
                        return false;
               
                case MotionEvent.ACTION_UP:
                	if(pTouchArea == TouchView.meleeSoldierButton){
            			if(TouchView.meleeSoldierButton.releaseTouchSuccessfull()) {
            				SoldierController.createSprite(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.52f, World.Team.GOOD);            				
            			}                		
            		}
            		else if(pTouchArea == TouchView.rangerSoldierButton){
            			if(TouchView.rangerSoldierButton.releaseTouchSuccessfull()) {
            				SoldierController.createAnimatedSprite(WorldView.MAP_WIDTH-300, WorldView.MAP_HEIGHT*0.52f, World.Team.GOOD);
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
		
//			if(pTouchArea == goodButton1){
//			    Log.d("TowerWars", timestamp + "");
//			        timestamp = Math.round(System.currentTimeMillis() / 1000);
//			        Log.d("TowerWars", "insidan");
//			        Dialog v = new BuildTowerView(this, WorldView.MAP_WIDTH - 500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
//			        v.show();
//			        
//			    }
		        /*
				if (tower == null) {
					Log.d("TowerWars", "s1Button");
					tower = TowerController.createTestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
					getEngine().getScene().getLastChild().attachChild(tower);
				}
				*/
				
//			}
//			else if(pTouchArea == goodButton2 ){
//				/*if (tower1 == null) {
//					Log.d("TowerWars", "s2Button");
//					tower1 = TowerController.createTestTower(WorldView.MAP_WIDTH - 700 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD); //new TestTower(World.MAP_WIDTH - 400 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT * 0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
//					getEngine().getScene().getLastChild().attachChild(tower1);
//				}*/
//
//                if (timestamp != Math.round(System.currentTimeMillis() / 1000) ) {
//                    timestamp = Math.round(System.currentTimeMillis() / 1000);
//                    Dialog v = new BuildTowerView(this, WorldView.MAP_WIDTH - 700 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT * 0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.GOOD);
//                    v.show();
//                    
//                   }			    
//			    
//			}
//			else if(pTouchArea == badButton1){
//				if (b1Tower == null) {
//					//b1Tower = TowerController.createTestTower(500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.44f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);//new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.4f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
//				}
//			}
//			else{
//				if (b2Tower == null) {
//					//b2Tower = TowerController.createTestTower(500 + this.mTowerTextureRegion.getWidth() * 0.25f, WorldView.MAP_HEIGHT*0.56f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL); //new TestTower(300 + this.mTowerTextureRegion.getWidth() * 0.25f, World.MAP_HEIGHT*0.6f - this.mTowerTextureRegion.getHeight() * 0.5f, this.mTowerTextureRegion, 200, World.Team.EVIL);
//				}
//			}
			
		return false;
		
	}


}
