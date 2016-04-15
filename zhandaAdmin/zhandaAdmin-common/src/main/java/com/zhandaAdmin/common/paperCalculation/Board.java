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
        float left=this.getElements().get(0).getButtonLeftPoint().getX();
        float right=this.getElements().get(0).getTopRightPoint().getX();
        float top=this.getElements().get(0).getTopLeftPoint().getY();
        float button=this.getElements().get(0).getButtonRightPoint().getY();
        Iterator<RectangleElement> iterator = elements.iterator();
        while (iterator.hasNext()){
            RectangleElement element = iterator.next();
            if(element.getButtonLeftPoint().getX()<left){
                left = element.getButtonLeftPoint().getX();
            }
            if(element.getTopRightPoint().getX()>right){
                right = element.getTopRightPoint().getX();
            }
            if(element.getTopLeftPoint().getY()>top){
                top = element.getTopLeftPoint().getY();
            }
            if(element.getButtonRightPoint().getY()<button){
                button = element.getButtonRightPoint().getY();
            }
        }
        float length = top-button;
        float width = right-left;
        if(this.area==null){
            area = new RectangleElement(null,length,width);
        }
        area.setLenth(length);
        area.setWidth(width);
        area.setPoint(left,button);
        return area;
    }


    public RectangleElement getMatchArea(float[] widths,float[] lenths) {
        RectangleElement area = getArea();
        float lenth = area.getLenth();
        float width = area.getWidth();
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
            this.matchArea = new RectangleElement(null,lenth,width,x,y);
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
                element.setLeft(null);
                if(neighbor!=null) {
                    neighbor.setRight(null);
                }
                link = new RectangleElement("-1",(float)(element.getLenth()*0.8),linkWidth);
                neighbor.setRight(link);
                newElements.add(link);
                break;
            case RIGHT:
                neighbor = element.getRight();
                element.setRight(null);
                if(neighbor!=null) {
                    neighbor.setLeft(null);
                }
                link = new RectangleElement("-1",(float)(element.getLenth()*0.8),linkWidth);
                element.setRight(link);
                this.elements.add(link);
                break;
            case BUTTOM:
                neighbor = element.getButtom();
                element.setButtom(null);
                if(neighbor!=null) {
                    neighbor.setTop(null);
                }
                link = new RectangleElement("-1",(float)(element.getWidth()*0.8),linkWidth);
                element.setButtom(link);
                this.elements.add(link);
                break;
            case TOP:
                neighbor = element.getTop();
                element.setTop(null);
                if(neighbor!=null) {
                    neighbor.setButtom(null);
                }
                link = new RectangleElement("-1",(float)(element.getWidth()*0.8),linkWidth);
                neighbor.setTop(link);
                newElements.add(link);
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
        if (element != null && source.contains(element)) {
            target.add(element);
            source.remove(element);
            move(source, target, element.getLeft());
            move(source, target, element.getRight());
            move(source, target, element.getTop());
            move(source, target, element.getButtom());
        }
    }

    public void init(int oriX,int oriY){
        getArea();
        float x = oriX-area.getPoint().getX();
        float y = oriY-area.getPoint().getY();
//        Iterator<RectangleElement> iterator = elements.iterator();
//        while (iterator.hasNext()){
//            RectangleElement element = iterator.next();
//            element.setPoint(element.getPoint().getX()+x,element.getPoint().getY()+y);
//        }
            elements.get(0).setPoint(elements.get(0).getPoint().getX()+x,elements.get(0).getPoint().getY()+y);
    }
    public  void init(){
        init(0,0);
    }
}
