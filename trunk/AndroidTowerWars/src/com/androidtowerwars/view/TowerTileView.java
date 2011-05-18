package com.androidtowerwars.view;

import org.anddev.andengine.entity.scene.Scene;

import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;

public class TowerTileView {
	
public void loadScene(Scene scene){
		
		int tileSpacing = 200;
		for (int n = 0; n < 6; n++) {
			// good towers
			ClickButton tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.44f,
					ButtonView.mButtonTextureRegion);
			TowerTile tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.44f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTile.towerTileListMap.get(World.Team.GOOD).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.56f,
					ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.56f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.GOOD);
			TowerTile.towerTileListMap.get(World.Team.GOOD).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			// evil towers
			tempSprite = new ClickButton(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.44f, ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.44f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTile.towerTileListMap.get(World.Team.EVIL).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			//scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.56f, ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(500 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.56f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(), World.Team.EVIL);
			TowerTile.towerTileListMap.get(World.Team.EVIL).add(
					tempTowerTile);
			TowerTile.towerTileSpriteMap.put(tempSprite,
					tempTowerTile);
			//scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
		}
	}

}
