package com.androidtowerwars.util;

public class Vector2d {
    private float x;
    private float y;

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(float[] v) {
        this.x = v[0];
        this.y = v[1];
    }

    public Vector2d(float[] v1, float[] v2) {
        this.x = v2[0] - v1[0];
        this.y = v2[1] - v1[1];
    }

    public Vector2d(float x1, float y1, float x2, float y2) {
        this.x = x2 - x1;
        this.y = y2 - y1;
    }

    public float getLength() {
        return (float) Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    public final void normalize() {
        if (getLength() != 0) {
            float length = getLength();
            x            = x/length;
            y            = y/length;
        }
    }

    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
}