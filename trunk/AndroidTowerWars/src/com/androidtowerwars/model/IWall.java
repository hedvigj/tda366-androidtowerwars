package com.androidtowerwars.model;

public interface IWall {
    public int getMaxHealth();
    public int getHealth();
    public void setHealth(int health); 
    public void setTeam(Team team);
    public Team getTeam();
}