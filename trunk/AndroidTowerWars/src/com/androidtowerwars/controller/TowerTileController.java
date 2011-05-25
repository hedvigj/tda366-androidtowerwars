package com.androidtowerwars.controller;

import java.util.concurrent.CopyOnWriteArrayList;

import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World;

public class TowerTileController {
    public TowerTileController() {
        World.getInstance().getPlayer(Team.GOOD).putTowerTileList(new CopyOnWriteArrayList<TowerTile>());
        World.getInstance().getPlayer(Team.EVIL).putTowerTileList(new CopyOnWriteArrayList<TowerTile>());
    }	
}