package com.zhandaAdmin.service;

/**
 * Created by Administrator on 2016/3/26.
 */
import com.zhandaAdmin.common.paperCalculation.Board;
import com.zhandaAdmin.common.paperCalculation.RectangleElement;

import java.applet.*;
import java.awt.*;

public class Rect extends Applet{
    public void paint(Graphics g){
        PaperService paper = new PaperService();
        try {
            Board newboard = paper.unfoldBox(99,120,34, PaperService.BoxType.NORMAL);
//            java.util.List<Board> boards = paper.split4(newboard);
//            for(int i=0;i<boards.size();i++) {
//                Board board =boards.get(i);
//                if(i>0) {
//                    board.init((int) boards.get(i - 1).getArea().getTopRightPoint().getX() + 10, 0);
//                }else{
//                    board.init();
//                }
//                paintBox(board,g);
//            }
            newboard.init();
            paintBox(newboard,g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void paintBox(Board board,Graphics g){
        g.setColor(Color.RED);
        g.fillRect((int) board.getArea().getPoint().getX(), (int) board.getArea().getPoint().getY(), (int) board.getArea().getWidth(), (int) board.getArea().getLength());
        g.setColor(Color.BLACK);
        for (RectangleElement e : board.getElements()) {
            g.drawRect((int) e.getPoint().getX(), (int) e.getPoint().getY(), (int) e.getWidth(), (int) e.getLength());
        }
    }
}
