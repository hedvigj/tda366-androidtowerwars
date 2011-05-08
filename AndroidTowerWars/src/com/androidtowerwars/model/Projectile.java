package com.androidtowerwars.model;

import java.util.Observable;

import android.util.Log;

import com.androidtowerwars.util.Vector2d;

public class Projectile extends Observable implements IProjectile, IObservableSprite{
	
	private float x;
	private float y;
	private float speed = 150f;
	private ISoldier target;
	private ITower parent;
	private Vector2d direction;
	
	public Projectile(ISoldier target, ITower parent) {
		this.target = target;
		this.parent = parent;
		this.x = parent.getX();
		this.y = parent.getY();
		updateDirection();
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public Vector2d getDirection() {
		updateDirection();
		return direction;
	}
	
	private void updateDirection() {
		direction = new Vector2d(x, y, target.getX(), target.getY());
		direction.normalize();
		Log.d("TowerWars", "x:"+x+", y:"+y);
	}
	
	public void setPosition(float x, float y) { 
		this.x = x;
		this.y = y;
		setChanged();
		notifyObservers();
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public ISoldier getTarget() {
		return target;
	}
	
	public void setTarget(ISoldier target) {
		this.target = target;
	}
	
	public ITower getParent() {
		return parent;
	}
	
	public void setParent(ITower parent) {
		this.parent = parent;
	}
}
