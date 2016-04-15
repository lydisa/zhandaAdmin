package com.zhandaAdmin.service;

import com.zhandaAdmin.common.paperCalculation.Board;
import com.zhandaAdmin.data.dao.entity.Company;

import java.util.List;

public interface IPaperService {

    Board unfoldBox(float length, float width, float high, PaperService.BoxType type) throws Exception;

    List<Board> split2(Board board) throws Exception;

    List<Board> split4(Board board) throws Exception;
}
