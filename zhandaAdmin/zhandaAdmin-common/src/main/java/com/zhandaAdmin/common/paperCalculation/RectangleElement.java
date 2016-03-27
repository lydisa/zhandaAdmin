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
    private RectangleElement button;
    public enum EDGE{
        LEFT,RIGHT,BUTTOM,TOP

    }

    public RectangleElement(float lenth, float width){
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
        float relX = x -this.point.getX();
        float relY = y -this.point.getY();
        move(relX,relY,this);
    }

    public RectangleElement getLeft() {
        return left;
    }

    public void setLeft(RectangleElement left) {
        if(left==null){
            this.left=null;
            return;
        }
        float x = this.point.getX()-left.width;
        float y = this.point.getY()+(this.lenth-right.lenth)/2;
        left.setPoint(x,y);
        left.right = this;
        this.left = left;
    }

    public RectangleElement getRight() {
        return right;
    }

    public void setRight(RectangleElement right) {
        if(right==null){
            this.right=null;
            return;
        }
        float x = this.point.getX()+this.width;
        float y = this.point.getY()+(this.lenth-right.lenth)/2;
        right.setPoint(x,y);
        right.left = this;
        this.right = right;
    }

    public RectangleElement getTop() {
        return top;
    }

    public void setTop(RectangleElement top) {
        if(top==null){
            this.top=null;
            return;
        }
        float x = this.point.getX()+(this.width-top.width)/2;
        float y = this.point.getY()+this.lenth;
        top.setPoint(x,y);
        top.button = this;
        this.top = top;
    }

    public RectangleElement getButton() {
        return button;
    }

    public void setButton(RectangleElement button) {
        if(button ==null){
            this.button =null;
            return;
        }
        float x = this.point.getX()+(this.width-top.width)/2;
        float y = this.point.getY()- button.lenth;
        button.setPoint(x,y);
        button.top = this;
        this.button = button;
    }

    public Point getButtonLeftPoint(){
        return this.point;
    }

    public Point getTopLeftPoint(){
        float y = this.point.getY()+this.lenth;
        return new Point(this.point.getX(),y);
    }

    public Point getButtonRightPoint(){
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
    public void move(float x,float y,RectangleElement entrance){
        this.point.setXY(this.point.getX()+x,this.point.getY()+y);
        if(this.getLeft()!=null&&this.getLeft()!=entrance){
            this.getLeft().move(x,y,this);
        }
        if(this.getRight()!=null&&this.getRight()!=entrance){
            this.getRight().move(x,y,this);
        }
        if(this.getTop()!=null&&this.getTop()!=entrance){
            this.getTop().move(x,y,this);
        }
        if(this.getButton()!=null&&this.getButton()!=entrance){
            this.getButton().move(x,y,this);
        }
    }
}
