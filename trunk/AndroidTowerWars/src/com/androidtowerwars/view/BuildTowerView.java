package com.androidtowerwars.view;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.androidtowerwars.R;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.model.World.Team;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class BuildTowerView extends Dialog {

    
    private final View keys[] = new View[3];
    
    private float pX; 
    private float pY; 
    private TextureRegion pTextureRegion;
    private float range;
    private Team team;
      
    public BuildTowerView(Context context,float pX, float pY, TextureRegion pTextureRegion, float range, Team team) {
        super(context);
        this.pX             = pX;
        this.pY             = pY;
        this.pTextureRegion = pTextureRegion;
        this.range          = range;
        this.team           = team;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Wie","körs nu");
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
                    sendBuild(t, pX, pY, pTextureRegion, range, team );
                }
            });
        }
    }
    
    private void sendBuild (int tower, float pX, float pY, TextureRegion pTextureRegion, float range, Team team) {
        
        switch (tower) {
        case 1:
            //build tower 1
            Log.d("Wie","wiiiieeeee");
            TowerController.createTestTower(pX, pY, pTextureRegion, range, team);
            break;
        case 2:
            //build tower 1
            TowerController.createTestTower(pX, pY, pTextureRegion, range, team);
            break;
        case 3:
            //build tower 1
            TowerController.createTestTower(pX, pY, pTextureRegion, range, team);
            break;
        }
        
        dismiss();
    }
}
