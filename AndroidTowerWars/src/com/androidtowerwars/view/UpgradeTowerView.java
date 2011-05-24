package com.androidtowerwars.view;

import com.androidtowerwars.R;
import com.androidtowerwars.controller.TowerController;
import com.androidtowerwars.model.TowerTile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class UpgradeTowerView extends Dialog {

    private TowerTile towerTile;
    
    public UpgradeTowerView(Context context, TowerTile towerTile) {
        super(context);
        this.towerTile = towerTile;
        // TODO Auto-generated constructor stub
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.sell_title);
        setContentView(R.layout.upgradeview);
        //findViews();
        setListeners();
    }
    
    private void setListeners() {
        
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //send delete
                TowerController.sellTower(towerTile);
                //WorldView.getInstance().getScene().getLastChild().detachChild(towerTile);
                dismiss();
            }
        });
        
      /*  findViewById(R.id.upgrade).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //send upgrade
                TowerController.upgradeTower(towerTile);
                dismiss();
            }
        });*/
    }

}
