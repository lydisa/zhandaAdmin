package com.zhandaAdmin.service;

/**
 * Created by Administrator on 2016/3/26.
 */
import com.zhandaAdmin.common.paperCalculation.Board;
import com.zhandaAdmin.common.paperCalculation.RectangleElement;

import java.applet.*;
import java.awt.*;
import java.util.*;

public class Rect extends Applet{
    public void paint(Graphics g){
//        g.draw3DRect(20,40,50,30,false);
        PaperCalculation paper = new PaperCalculation();
        try {
            Board newboard = paper.unfoldBox(88,50,34, PaperCalculation.BoxType.NORMAL);
            java.util.List<Board> boards = paper.split2(newboard);
            for(int i=0;i<boards.size();i++) {
                Board board =boards.get(i);
                if(i>0) {
                    board.init((int) boards.get(i - 1).getArea().getTopRightPoint().getX() + 10, 0);
                }
                g.setColor(Color.RED);
                g.fillRect((int) board.getArea().getPoint().getX(), (int) board.getArea().getPoint().getY(), (int) board.getArea().getWidth(), (int) board.getArea().getLenth());
                g.setColor(Color.BLACK);
                for (RectangleElement e : board.getElements()) {
                    g.drawRect((int) e.getPoint().getX(), (int) e.getPoint().getY(), (int) e.getWidth(), (int) e.getLenth());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
