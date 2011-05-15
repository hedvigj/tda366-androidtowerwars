package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.androidtowerwars.R;
import com.androidtowerwars.model.TowerTile;
import com.androidtowerwars.model.World.Team;



public class BuildTowerView extends Dialog {

    
    private final View keys[] = new View[3];
    private TowerTile towerTile;
      
    public BuildTowerView(Context context, TowerTile towerTile) {
        super(context);
        this.towerTile = towerTile;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.buildpop_title);
        setContentView(R.layout.popuppad);
        findViews();
        setListeners();
    }
    
    private void findViews() {
        keys[0] = findViewById(R.id.keypad_1);
        keys[1] = findViewById(R.id.keypad_2);
        keys[2] = findViewById(R.id.keypad_3);   
    }
    
    private void setListeners() {
        for (int i = 0; i < keys.length; i++) {
            final int t = i + 1;
            keys[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sendBuild(t, towerTile);
                }
            });
        }
    }
    
    private void sendBuild (int tower, TowerTile towerTile) {
        
        switch (tower) {
        case 1:
            //build tower 1
            //TowerController.createTestTower(pX, pY, pTextureRegion, range, team);
        	towerTile.action();
            break;
        case 2:
            //build tower 1
            //TowerController.createTestTower(pX, pY, pTextureRegion, range, team);
        	towerTile.action();
            break;
        case 3:
            //build tower 1
            //TowerController.createTestTower(pX, pY, pTextureRegion, range, team);
        	towerTile.action();
            break;
        }
        
        dismiss();
    }
}
