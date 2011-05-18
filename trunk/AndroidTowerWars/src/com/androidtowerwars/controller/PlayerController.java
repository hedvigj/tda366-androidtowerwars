package com.androidtowerwars.controller;

import java.util.HashMap;
import java.util.Map;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.World;
import com.androidtowerwars.model.logic.ArtificialIntelligence;
import com.androidtowerwars.model.logic.TowerLogic;
import com.androidtowerwars.view.WorldView;

public class PlayerController {
	public static Map<World.Team, Player> playerMap = new HashMap<World.Team, Player>();
	
	public PlayerController() {
		Player evilPlayer = new Player(World.Team.EVIL);
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
		playerMap.put(World.Team.GOOD, new Player(World.Team.GOOD));
		playerMap.put(World.Team.EVIL, evilPlayer);
		
	}
	
}
