package com.androidtowerwars.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.androidtowerwars.model.Player;
import com.androidtowerwars.model.Team;
import com.androidtowerwars.model.World;
import com.androidtowerwars.view.TouchView;
import com.androidtowerwars.view.WorldView;

public class PlayerController {
    public static Player victor = null;
    public PlayerController() {
        Player evilPlayer                       = World.getPlayer(Team.EVIL);
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
        
        final TimerHandler goldTimerHandler = new TimerHandler(15,
                new ITimerCallback() {
            public void onTimePassed(
                    final TimerHandler pTimerHandler) {
                pTimerHandler.reset();
                World.getPlayer(Team.GOOD).increaseGold(10); //Players earn 10 gold every 15 seconds
                World.getPlayer(Team.EVIL).increaseGold(10);
            }
        });
        WorldView.getInstance().registerUpdateHandler(goldTimerHandler); 
    }

    public static void gameOver(boolean victory) {
        if (victor == null) {
            if (victory) {
                victor = World.getPlayer(Team.GOOD);
                TouchView.gameOver(true);
            } else {
                victor = World.getPlayer(Team.EVIL);
                TouchView.gameOver(false);
            }
        }
    }
}
