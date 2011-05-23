package com.androidtowerwars.view;

import com.androidtowerwars.R;
import com.androidtowerwars.model.TowerTile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class UpgradeTowerView extends Dialog {

    public UpgradeTowerView(Context context, TowerTile towerTile) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.buildpop_title);
        setContentView(R.layout.upgradeview);
        //findViews();
        //setListeners();
    }

}
