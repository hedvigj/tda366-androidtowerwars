package com.androidtowerwars.view;

import java.util.concurrent.ConcurrentHashMap;

import org.anddev.andengine.entity.scene.Scene;

import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;

public class TowerTileView {

	public static ConcurrentHashMap<ClickButton, TowerTile> towerTileSpriteMap = new ConcurrentHashMap<ClickButton, TowerTile>();

	public void loadScene(Scene scene) {

		int tileSpacing = 200;
		for (int n = 0; n < 6; n++) {
			// good towers
			ClickButton tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.49f,
					ButtonView.mButtonTextureRegion);
			TowerTile tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (700 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.49f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(),
					Team.GOOD);
			World.getInstance().getPlayer(Team.GOOD).addTowerTiles(tempTowerTile);
			TowerTileView.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(WorldView.MAP_WIDTH
					- (500 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.75f,
					ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(WorldView.MAP_WIDTH
					- (500 + (tileSpacing * n)), WorldView.MAP_HEIGHT * 0.75f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(),
					Team.GOOD);
			World.getInstance().getPlayer(Team.GOOD).addTowerTiles(tempTowerTile);
			TowerTileView.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			// evil towers
			tempSprite = new ClickButton(350 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.49f,
					ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(350 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.49f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(),
					Team.EVIL);
			World.getInstance().getPlayer(Team.EVIL).addTowerTiles(tempTowerTile);
			TowerTileView.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			// scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);

			tempSprite = new ClickButton(550 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.75f,
					ButtonView.mButtonTextureRegion);
			tempTowerTile = new TowerTile(550 + (tileSpacing * n),
					WorldView.MAP_HEIGHT * 0.75f,
					ButtonView.mButtonTextureRegion.getWidth(),
					ButtonView.mButtonTextureRegion.getHeight(),
					Team.EVIL);
			World.getInstance().getPlayer(Team.EVIL).addTowerTiles(tempTowerTile);
			TowerTileView.towerTileSpriteMap.put(tempSprite, tempTowerTile);
			// scene.registerTouchArea(tempSprite);
			scene.getLastChild().attachChild(tempSprite);
		}
	}

}
