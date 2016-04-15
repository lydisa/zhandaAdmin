package com.zhandaAdmin.controller;

import com.zhandaAdmin.common.dao.CommonResult;
import com.zhandaAdmin.common.paperCalculation.Board;
import com.zhandaAdmin.data.dao.entity.Company;
import com.zhandaAdmin.service.ICompanyService;
import com.zhandaAdmin.service.IPaperService;
import com.zhandaAdmin.service.PaperService;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/4/13.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    @Qualifier("paperService")
    private IPaperService paperService;

    @RequestMapping(value = "/getBoards", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getBoards(@RequestBody Company company) throws Exception {

        Board board = paperService.unfoldBox(99,120,34, PaperService.BoxType.NORMAL);
        List<Board> boards = paperService.split4(board);
        for(Board board1 :boards){
            board1.init();
            board1.getMatchArea(null,null);
        }
        //输出处理
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(new String[]{
                "left","right","top","buttom"
        });
        ;
        //JSONArray subMsgs = JSONArray.fromObject(boards.toArray(),config);
        CommonResult result = null;
        if(boards != null)
            result = new CommonResult("0","success",JSONSerializer.toJSON(boards,config));
        else
            result =new CommonResult("-1","failed",null);
        return result;

    }
}
