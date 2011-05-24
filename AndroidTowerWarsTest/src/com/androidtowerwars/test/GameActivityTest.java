package com.androidtowerwars.test;

import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.androidtowerwars.GameActivity;
import com.androidtowerwars.model.Soldier;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.Tower;
import com.androidtowerwars.model.World;

import junit.framework.Assert;

public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {

    private GameActivity mActivity;
    private Tower tower;
    public GameActivityTest() {
        
        super("com.androidtowerwars", GameActivity.class);
        Log.d("Test", "wie");
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();

    }
   /* void TestCase() {
       Assert.assertEquals(true, true);
    }
    */
    /*
    public void testgetGold() {
       //Assert.assertTrue(true);
        //Log.d("Test", "wie");
       Assert.assertEquals(80, World.getPlayer(Team.GOOD).getGold());

       
       //Soldier soldier = new Soldier(pX, pY, 5, team);
       
    }
*/
   public void testgetCost() {
        //Soldier S = new Soldier(300, 200, 5, Team.GOOD);
        Assert.assertEquals(20, new Tower(800, 800, 100, Team.GOOD).getCost());
    }
    
    
   /* public void testtrainSoldier() {
        Soldier S = new Soldier(300, 200, 5, Team.GOOD);
        S.getCost();
    }*/
    

    public void testcreateTestTower() {
        Tower t = new Tower(800, 800, 100, Team.GOOD);
        this.tower = t;
    }
}
