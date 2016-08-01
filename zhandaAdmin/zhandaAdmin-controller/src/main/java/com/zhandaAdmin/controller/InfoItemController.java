package com.zhandaAdmin.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhandaAdmin.data.dao.entity.AttrPath;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.zhandaAdmin.common.dao.CommonResult;
import com.zhandaAdmin.service.InfoDemo;



/**
 * Created by admin on 2016/7/22.
 */
@Controller
@RequestMapping("/info")
public class InfoItemController{
    @RequestMapping(value = "/getInfoItems", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getInfoItems(
            @RequestBody Map<String, Object> params) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        try {
            String className = (String) params.get("className");
            Gson gson = new Gson();
            Object bean = params.get("bean");
            JsonElement beanJson = gson.toJsonTree(bean);
            Class clazz = Class.forName(className);
            Object dto = gson.fromJson(beanJson,clazz);
            InfoDemo demo = new InfoDemo();
            List<AttrPath> path = new ArrayList<AttrPath>();
            Map<String, Object>  infoItems = demo.getBusiInfo(dto, null, "info",null,path,null,dto);
            demo.paveMap(infoItems);
            //System.out.print(infoItems);
            return new CommonResult("200",
                    "转换成功咯");
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return new CommonResult("500",
                    e.getMessage());
        }
    }

}
