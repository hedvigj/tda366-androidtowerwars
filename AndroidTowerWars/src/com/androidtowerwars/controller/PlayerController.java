package com.androidtowerwars.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.WorldView;

public class PlayerController {
	
	public PlayerController() {
		Player evilPlayer = World.getInstance().getPlayer(Team.EVIL);
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
		World.playerMap.put(Team.GOOD, World.getInstance().getPlayer(Team.GOOD));
		World.playerMap.put(Team.EVIL, evilPlayer);
		
	}
	
}
