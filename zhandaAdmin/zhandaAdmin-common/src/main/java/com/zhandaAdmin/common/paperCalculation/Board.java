package com.zhandaAdmin.common.paperCalculation;

import com.zhandaAdmin.common.tools.MathTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2016/3/24.
 */
public class Board {
    private List<RectangleElement> elements;
    private RectangleElement area;
    private RectangleElement matchArea;

    public List<RectangleElement> getElements() {
        return elements;
    }

    public void setElements(List<RectangleElement> elements) {
        this.elements = elements;
    }

    public RectangleElement getArea() {
        float left=0;
        float right=0;
        float top=0;
        float buttom=0;
        Iterator<RectangleElement> iterator = elements.iterator();
        while (iterator.hasNext()){
            RectangleElement element = iterator.next();
            if(element.getButtomLeftPoint().getX()<left){
                left = element.getButtomLeftPoint().getX();
            }
            if(element.getTopRightPoint().getX()>right){
                right = element.getTopRightPoint().getX();
            }
            if(element.getTopLeftPoint().getY()>top){
                top = element.getTopLeftPoint().getY();
            }
            if(element.getButtomRightPoint().getY()<buttom){
                buttom = element.getButtomRightPoint().getY();
            }
        }
        float lenth = top-buttom;
        float width = right-left;
        if(this.area==null){
            area = new RectangleElement(lenth,width);
        }
        area.setLenth(lenth);
        area.setWidth(width);
        area.setPoint(left,buttom);
        return area;
    }


    public RectangleElement getMatchArea(float[] widths,float[] lenths) {
        float lenth = getArea().getLenth();
        float width = getArea().getWidth();
        if(widths!=null){
            width = MathTools.binarySearchKey(widths,this.getArea().getWidth());
        }
        if(lenths!=null){
            lenth = MathTools.binarySearchKey(lenths,this.getArea().getLenth());
        }
        Point center = getArea().getCenterPoint();
        float x = center.getX() - width/2;
        float y = center.getY() - lenth/2;
        if(this.matchArea==null){
            this.matchArea = new RectangleElement(lenth,width,x,y);
        }else{
            this.matchArea.setWidth(width);
            this.matchArea.setLenth(lenth);
            this.matchArea.setPoint(x,y);
        }
        return matchArea;
    }

    public Board split(RectangleElement element,RectangleElement.EDGE splitEdge,float linkWidth,RectangleElement.EDGE linkEdge) throws Exception {
        if(!this.elements.contains(element)){
            throw new Exception("不对哦");
        }
        Board newBoard = new Board();
        List<RectangleElement> newElements = new ArrayList<RectangleElement>();
        RectangleElement link;
        RectangleElement neighbor = null;
        switch(splitEdge){
            case LEFT:
                neighbor = element.getLeft();
                link = new RectangleElement((float)(element.getLenth()*0.8),linkWidth);
                neighbor.setRight(link);
                break;
            case RIGHT:
                neighbor = element.getRight();
                link = new RectangleElement((float)(element.getLenth()*0.8),linkWidth);
                element.setRight(link);
                break;
            case BUTTOM:
                neighbor = element.getButtom();
                link = new RectangleElement((float)(element.getWidth()*0.8),linkWidth);
                element.setButtom(link);
                break;
            case TOP:
                neighbor = element.getTop();
                link = new RectangleElement((float)(element.getWidth()*0.8),linkWidth);
                neighbor.setTop(link);
                break;
        }
        if(neighbor==null){
            return null;
        }
        move(this.elements,newElements,neighbor);
        newBoard.setElements(newElements);
        return newBoard;
    }

    private void move(List<RectangleElement> source, List<RectangleElement> target,RectangleElement element) {
        if (target != null) {
            target.add(element);
            source.remove(element);
            move(source, target, element.getLeft());
            move(source, target, element.getRight());
            move(source, target, element.getTop());
            move(source, target, element.getButtom());
        }
    }
}