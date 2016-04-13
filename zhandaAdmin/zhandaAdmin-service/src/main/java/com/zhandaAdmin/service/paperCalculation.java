package com.zhandaAdmin.service;

import com.zhandaAdmin.common.paperCalculation.Board;
import com.zhandaAdmin.common.paperCalculation.RectangleElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class PaperCalculation {
    enum BoxType{
        NORMAL,WITHCOVER
    }
    public Board unfoldBox(float length,float width,float high,BoxType type) throws Exception {
        Board board = new Board();
        List<RectangleElement> elements = new ArrayList<RectangleElement>();
        switch (type){
            case NORMAL:
                for(int i = 0;i<2;i++){
                    RectangleElement a = new RectangleElement(high+width,length);
                    RectangleElement b = new RectangleElement(high+width,width);
                    elements.add(a);
                    elements.add(b);
                }
                for(int i = 0;i<3;i++){
                    elements.get(i).setRight(elements.get(i+1));
                }
                break;
            case WITHCOVER:
                break;
        }
        board.setElements(elements);
        board.split(board.getElements().get(3), RectangleElement.EDGE.RIGHT,5, RectangleElement.EDGE.RIGHT);
        return board;
    }

    public List<Board> split2(Board board) throws Exception {
        List<Board> list = new ArrayList<Board>();
        Board newBoard = board.split(board.getElements().get(2), RectangleElement.EDGE.LEFT,5, RectangleElement.EDGE.RIGHT);
        newBoard.init();
        list.add(newBoard);
        list.add(board);
        return list;
    }

    public List<Board> split4(Board board) throws Exception {
        List<Board> list = new ArrayList<Board>();

        for(int i = 0;i<3;i++){
            Board newBoard =board.split(board.getElements().get(0), RectangleElement.EDGE.RIGHT,5, RectangleElement.EDGE.RIGHT);
            list.add(board);
            board = newBoard;
        }
        list.add(board);
        return list;
    }
}
