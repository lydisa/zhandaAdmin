package com.zhandaAdmin.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zhandaAdmin.common.dao.CommonResult;
import com.zhandaAdmin.service.InfoDemo;
//import org.apache.commons.beanutils.BeanUtils;
import net.sf.json.groovy.GJson;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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
            Object infoItems = demo.getBusiInfo(dto, null, null);
            System.out.print(infoItems);
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
