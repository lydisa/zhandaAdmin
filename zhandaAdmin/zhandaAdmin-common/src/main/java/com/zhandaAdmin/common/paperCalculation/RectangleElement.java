package com.zhandaAdmin.common.paperCalculation;

/**
 * Created by admin on 2016/3/24.
 */
public class RectangleElement {
    private float lenth;
    private float width;
    private Point point;
    private RectangleElement left;
    private RectangleElement right;
    private RectangleElement top;
    private RectangleElement buttom;

    RectangleElement(float lenth,float width){
        this.point = new Point(0,0);
        this.lenth = lenth;
        this.width = width;
    }

    RectangleElement(float lenth,float width,Point point){
        this.point = point;
        this.lenth = lenth;
        this.width = width;
    }

    RectangleElement(float lenth,float width,float x,float y){
        this.point = new Point(x,y);
        this.lenth = lenth;
        this.width = width;
    }

    public float getLenth() {
        return lenth;
    }

    public void setLenth(float lenth) {
        this.lenth = lenth;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPoint(float x,float y) {
        this.point.setXY(x,y);
    }

    public RectangleElement getLeft() {
        return left;
    }

    public void setLeft(RectangleElement left) {
        float x = this.point.getX()-left.width;
        float y = this.point.getY()+(this.lenth-right.lenth)/2;
        left.setPoint(x,y);
        this.left = left;
    }

    public RectangleElement getRight() {
        return right;
    }

    public void setRight(RectangleElement right) {
        float x = this.point.getX()+this.width;
        float y = this.point.getY()+(this.lenth-right.lenth)/2;
        right.setPoint(x,y);
        this.right = right;
    }

    public RectangleElement getTop() {
        return top;
    }

    public void setTop(RectangleElement top) {
        float x = this.point.getX()+(this.width-top.width)/2;
        float y = this.point.getY()+this.lenth;
        top.setPoint(x,y);
        this.top = top;
    }

    public RectangleElement getButtom() {
        return buttom;
    }

    public void setButtom(RectangleElement buttom) {
        float x = this.point.getX()+(this.width-top.width)/2;
        float y = this.point.getY()-buttom.lenth;
        buttom.setPoint(x,y);
        this.buttom = buttom;
    }

    public Point getButtomLeftPoint(){
        return this.point;
    }

    public Point getTopLeftPoint(){
        float y = this.point.getY()+this.lenth;
        return new Point(this.point.getX(),y);
    }

    public Point getButtomRightPoint(){
        float x = this.point.getX() + this.width;
        return new Point(x,this.point.getY());
    }

    public Point getTopRightPoint(){
        float x = this.point.getX() + width;
        float y = this.point.getY() + lenth;
        return new Point(x,y);
    }

    public  Point getCenterPoint(){
        float x = this.point.getX() + width;
        float y = this.point.getY() + lenth;
        return new Point(x/2,y/2);
    }
}
