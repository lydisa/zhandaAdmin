package com.zhandaAdmin.common.paperCalculation;

/**
 * Created by admin on 2016/3/24.
 */
public class Point {
    private float x;
    private float y;

    Point(float x,float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setXY(float x,float y){
        this.x = x;
        this.y = y;
    }

}
