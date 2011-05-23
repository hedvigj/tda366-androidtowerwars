package com.androidtowerwars.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.view.WorldView;

public class PlayerController {
	
	public PlayerController() {
		Player evilPlayer = new Player(Team.EVIL);
		ArtificialIntelligenceController evilAI = new ArtificialIntelligenceController(evilPlayer);
		evilPlayer.addObserver(evilAI);
         final TimerHandler timerHandler = new TimerHandler(evilAI.getUpdateInterval(),
                 new ITimerCallback() {
                     public void onTimePassed(
                             final TimerHandler pTimerHandler) {
                         pTimerHandler.reset();
                         ArtificialIntelligenceController.updateByTime();
                     }
                 });
         WorldView.getInstance().registerUpdateHandler(timerHandler);
		Player.playerMap.put(Team.GOOD, new Player(Team.GOOD));
		Player.playerMap.put(Team.EVIL, evilPlayer);
		
	}
	
}
