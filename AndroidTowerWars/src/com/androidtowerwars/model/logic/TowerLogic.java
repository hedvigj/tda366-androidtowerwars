package com.androidtowerwars.model.logic;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.androidtowerwars.controller.ProjectileController;
import com.androidtowerwars.model.ITower;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.RangerView;
import com.androidtowerwars.view.SoldierView;

public class TowerLogic {

    public static synchronized void updateTower(ITower tower) {
        Team team = tower.getTeam();
        Rectangle range = tower.getRange();
        for (int n = 0; n < World.getInstance().getPlayer(team.opposite())
        .getBarrack().getSoldiers().size(); n++) {
            if (range.collidesWith(SoldierView.soldierSpriteMap.get(World
                    .getInstance().getPlayer(team.opposite()).getBarrack()
                    .getSoldiers().get(n)))) {
                ProjectileController.createSprite(World.getInstance()
                        .getPlayer(team.opposite()).getBarrack().getSoldiers()
                        .get(n), tower);
                break;
            } else if (range.collidesWith(RangerView.rangerSpriteMap.get(World
                    .getInstance().getPlayer(team.opposite()).getBarrack()
                    .getSoldiers().get(n)))) {
                ProjectileController.createSprite(World.getInstance()
                        .getPlayer(team.opposite()).getBarrack().getSoldiers()
                        .get(n), tower);
                break;
            }
        }
    }
}