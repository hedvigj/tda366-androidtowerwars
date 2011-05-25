package com.androidtowerwars.model.logic;

import com.androidtowerwars.model.Player;

public class ArtificialIntelligence {
    public static Player evilPlayer;

    public static boolean evilRich(){
        if (evilPlayer.getGold() > 125) {
            return true;
        }
        return false;
    }

    public static boolean evilLessRich() {
        if (evilPlayer.getGold() > 42) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean evilSurpriseSmall() {
        if (Math.random() <= 0.3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean evilSurpriseBig() {
        if (Math.random() <= 0.2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean evilSurpriseMedium() {
        if (Math.random() <= 0.2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean evilKindaPoor() {
        if (evilPlayer.getGold() > 39) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean evilPoor() {
        if (evilPlayer.getGold() > 5) {
            return true;
        } else {
            return false;
        }
    }
}