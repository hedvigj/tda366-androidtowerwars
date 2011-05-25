package com.androidtowerwars.model;

public class World {
    private static Player goodPlayer = new Player(Team.GOOD);
    private static Player evilPlayer = new Player(Team.EVIL);
    private static World instance    = null;

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public static Player getPlayer(Team team) {
        if (team == Team.EVIL) {
            return evilPlayer;
        } else {
            return goodPlayer;
        }
    }
}