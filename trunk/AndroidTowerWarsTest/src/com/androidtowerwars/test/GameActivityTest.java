package com.androidtowerwars.test;


import android.test.InstrumentationTestCase;

import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.Wall;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.WallLogic;
/*
 * Because of the Rectange class for range in both Tower and Soldier
 * we can't test anything of value, since that can't be reached from here
 * for some reason. Pretty much meaning that the lack of valuable test is from 
 * time missing to fix the problem.
 */

public class GameActivityTest extends InstrumentationTestCase {
    protected void setUp() throws Exception {
        super.setUp();
    }


    public void testGetGold() {
       assertEquals(80, World.getInstance().getPlayer(Team.GOOD).getGold());
    } 
    
    public void testtrainSoldier() {
        Soldier S = new Soldier(300, 200, 5, Team.GOOD);
        S.getCost();
    }
    

    public void testCreateTestTower() {
        Tower k = new Tower(80, 80, 10, Team.GOOD);
        assertNotNull(k);
    }
    
    public void testCreatePlayer() {
        Player p = new Player(Team.GOOD);
        assertNotNull(p);
    }
    
    public void testDecreaseHPCastle() {
    	Wall wall = new Wall(Team.GOOD);
    	wall.setHealth(wall.getHealth()-10);
    	int i = wall.getHealth();
    	assertEquals(90, i);
    }
    
    public void testDamageCastle() {
    	Wall wall1 = new Wall(Team.GOOD);
    	WallLogic.damageCastle(wall1);
    	int i = wall1.getHealth();
    	assertEquals(90, i);
    }
       
}
