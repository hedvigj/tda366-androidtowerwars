package com.androidtowerwars.view;

import com.androidtowerwars.R;
import com.androidtowerwars.R.id;
import com.androidtowerwars.R.layout;
import com.androidtowerwars.R.string;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.model.World;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


public class BuildTowerView extends Dialog {

    
    private final View keys[] = new View[3];
      
    public BuildTowerView(Context context) {
        super(context);

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
                    // Perform action on clicks
                    sendBuild(t);
                    Log.d("Wie","wiiiieeeee");
                    
                }
            });
        }
    }
    
    private void sendBuild (int tower) {
        //link to towerbuilder?
        dismiss();
    }
}
