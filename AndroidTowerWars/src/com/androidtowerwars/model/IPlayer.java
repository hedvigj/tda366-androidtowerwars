package com.androidtowerwars.model;

public interface IPlayer {
    public int getGold();
    public Team getTeam();
    public void setGold(int gold);
    public void setTeam(Team team);
    public void decreaseGold(int cost);
    public void increaseGold(int earnings);
    public void updateAI();
    public Barrack getBarrack();
}