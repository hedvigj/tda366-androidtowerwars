package com.androidtowerwars.test;

import android.test.InstrumentationTestCase;


import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.World;


public class GameActivityTest extends InstrumentationTestCase {

//    private GameActivity mActivity;
    private Tower k;
//    public GameActivityTest() {
//        super("com.androidtowerwars", GameActivity.class);
        //testCreateTestTower(t);
//    }
    protected void setUp() throws Exception {
        super.setUp();
        //mActivity = this.getActivity();
        
        //Tower t = new Tower(800, 800, 100, Team.GOOD);
        /*testCreateTestTower(t);
        Soldier S = new Soldier(300, 200, 5, Team.GOOD);*/
        //testGetCost(S);
    }

   /* void TestCase() {
       Assert.assertEquals(true, true);
    }
    */
    //@SmallTest
    public void testGetGold() {
       //Assert.assertTrue(true);
        //Log.d("Test", "wie");
       assertEquals(80, World.getInstance().getPlayer(Team.GOOD).getGold());
        //assertEquals(10, 10); 
       
       //Soldier soldier = new Soldier(pX, pY, 5, team);
       
    } /*
    @SmallTest
    public void testGetCost(Soldier S) {
        //Soldier S = new Soldier(300, 200, 5, Team.GOOD);
        Assert.assertEquals(5, S.getCost());
    }
    
    */
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
        assertNotNull(k);
    }
}
