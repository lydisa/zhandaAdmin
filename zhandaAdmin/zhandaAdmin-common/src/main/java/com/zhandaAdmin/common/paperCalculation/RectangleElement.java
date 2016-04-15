package com.zhandaAdmin.common.paperCalculation;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by admin on 2016/3/24.
 */
public class RectangleElement {
    private String id;
    private float length;
    private float width;
    private Point point;
    private RectangleElement left;
    private RectangleElement right;
    private RectangleElement top;
    private RectangleElement buttom;
    private String leftId;
    private String rightId;
    private String topId;
    private String buttonId;
    public enum EDGE{
        LEFT,RIGHT,BUTTOM,TOP

    }

    public RectangleElement(String id, float length, float width){
        this.point = new Point(0,0);
        this.length = length;
        this.width = width;
        this.id = id;
    }

    RectangleElement(String id, float length, float width, Point point){
        this.point = point;
        this.length = length;
        this.width = width;
        this.id = id;
    }

    RectangleElement(String id, float length, float width, float x, float y){
        this.point = new Point(x,y);
        this.length = length;
        this.width = width;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
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

    @JsonIgnore
    public RectangleElement getLeft() {
        return left;
    }

    public void setLeft(RectangleElement left) {
        if(left==null){
            this.left=null;
            this.leftId=null;
            return;
        }
        float x = this.point.getX()-left.width;
        float y = this.point.getY()+(this.length -right.length)/2;
        left.setPoint(x,y);
        left.right = this;
        left.rightId =this.id;
        this.left = left;
        this.leftId = left.id;
    }

    @JsonIgnore
    public RectangleElement getRight() {
        return right;
    }

    public void setRight(RectangleElement right) {
        if(right==null){
            this.right=null;
            this.rightId=null;
            return;
        }
        float x = this.point.getX()+this.width;
        float y = this.point.getY()+(this.length -right.length)/2;
        right.setPoint(x,y);
        right.left = this;
        right.leftId = this.id;
        this.right = right;
        this.rightId = right.id;
    }

    @JsonIgnore
    public RectangleElement getTop() {
        return top;
    }

    public void setTop(RectangleElement top) {
        if(top==null){
            this.top=null;
            this.topId=null;
            return;
        }
        float x = this.point.getX()+(this.width-top.width)/2;
        float y = this.point.getY()+this.length;
        top.setPoint(x,y);
        top.buttom = this;
        top.buttonId = this.id;
        this.top = top;
        this.topId = top.id;
    }

    @JsonIgnore
    public RectangleElement getButtom() {
        return buttom;
    }

    public void setButtom(RectangleElement buttom) {
        if(buttom ==null){
            this.buttom =null;
            this.buttonId=null;
            return;
        }
        float x = this.point.getX()+(this.width-top.width)/2;
        float y = this.point.getY()- buttom.length;
        buttom.setPoint(x,y);
        buttom.top = this;
        buttom.topId=this.id;
        this.buttom = buttom;
        this.buttonId = buttom.id;
    }

    public Point getButtonLeftPoint(){
        return this.point;
    }

    public Point getTopLeftPoint(){
        float y = this.point.getY()+this.length;
        return new Point(this.point.getX(),y);
    }

    public Point getButtonRightPoint(){
        float x = this.point.getX() + this.width;
        return new Point(x,this.point.getY());
    }

    public Point getTopRightPoint(){
        float x = this.point.getX() + width;
        float y = this.point.getY() + length;
        return new Point(x,y);
    }

    public  Point getCenterPoint(){
        float x = this.point.getX() + width;
        float y = this.point.getY() + length;
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
        if(this.getButtom()!=null&&this.getButtom()!=entrance){
            this.getButtom().move(x,y,this);
        }
    }

    public String getLeftId() {
        return leftId;
    }

    public void setLeftId(String leftId) {
        this.leftId = leftId;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getTopId() {
        return topId;
    }

    public void setTopId(String topId) {
        this.topId = topId;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }
}
