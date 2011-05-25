package com.androidtowerwars.model;

public enum Team {
    GOOD, EVIL;
    
    public Team opposite() {
        if (this == Team.EVIL)
            return Team.GOOD;
        else
            return Team.EVIL;
    }
}