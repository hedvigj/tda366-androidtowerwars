package com.androidtowerwars;



import com.androidtowerwars.view.AboutView;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;


public class StartGame extends Activity implements OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View continueButton = findViewById(R.id.continue_button);
       	continueButton.setOnClickListener(this);
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.about_button:
            Intent i = new Intent(this, AboutView.class);
            startActivity(i);
            break;
        case R.id.new_button:
            //openNewGameDialog();
            startGame();
            break;
        case R.id.exit_button:
            finish();
            break;
            //more buttons go here (if any...)
            
        }
        
    }
    
    private void startGame() {
        Log.d("Test", "clicked on " );
        Intent intent = new Intent(this, GameActivity.class);
        //intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
        //GameActivity g = new GameActivity();
    }   
    
}
