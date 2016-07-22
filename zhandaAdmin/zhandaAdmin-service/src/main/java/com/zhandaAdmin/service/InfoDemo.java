package com.zhandaAdmin.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhandaAdmin.data.dao.entity.BaseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import com.zhandaAdmin.data.dao.entity.AttrInfoTemplet;
import com.zhandaAdmin.data.dao.entity.AttrInfo;
import com.zhandaAdmin.data.dao.entity.InfoRule;
import org.springframework.util.CollectionUtils;


/**
 * Created by admin on 2016/7/20.
 */
public class InfoDemo {
    public Map<String, Object> getBusiInfo(Object bean, Object busiQryCond, String className)
            throws IllegalAccessException {
        Map<String, Object> infos = new HashMap<String, Object>();
        Class clazz = bean.getClass();
        // 获取dto类名
        if (StringUtils.isBlank(className)) {
            String[] classPath = clazz.getName().split("\\.");
            className = classPath[classPath.length - 1];
        }
        // 根据dto类名获取该dto下所有属性的信息项模板
        List<AttrInfoTemplet> infoTplts = getAttrInfoTempletsByClassName(
                className, busiQryCond);
        // 根据信息项模板和实例数据生成信息项实例
        Map<String, Object> attrInfos = createInfos(infoTplts, bean);
        infos = attrInfos;
        if(infos==null){
            infos = new HashMap<String, Object>();
        }
        //遍历所有属性,当属性是继承AppBaseDTO的对象时继续匹配该属性的对象的信息项
        Field[] fields;
        fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = fields[i].get(bean);
            // 当属性是数组时
            if (value instanceof List) {
                List<Object> list = (List<Object>) value;
                List<Object> listInfo = new ArrayList<Object>();
                for (Object attr : list) {
                    Object subInfo = getAttrInfos(attr,
                            busiQryCond);
                    if (subInfo != null) {
                        listInfo.add(subInfo);
                    }
                }
                if (listInfo.size() > 0) {
                    infos.put(fields[i].getName(), listInfo);
                }
                continue;
            }
            Object info = getAttrInfos(fields[i].get(bean),
                    busiQryCond);
            if (info != null) {
                infos.put(fields[i].getName(), info);
            }
        }
        if (infos.size() == 0) {
            return null;
        }
        return infos;
    }


    // 当属性是对象时继续深入匹配信息项
    private Object getAttrInfos(Object value,
                                Object busiQryCond) throws IllegalAccessException {
        if (value == null) {
            return null;
        }
        // 当属性是子对象时迭代
        if (value instanceof BaseEntity) {
            Map<String, Object> subBusiInfos = getBusiInfo(value, busiQryCond, null);
            return subBusiInfos;
        }
        return null;
    }

    // 根据信息项列表和实例数据生成信息项实例
    private Map<String, Object> createInfos(List<AttrInfoTemplet> infoTplts,
                                            Object bean) throws IllegalAccessException {
        if (infoTplts == null || infoTplts.size() == 0) {
            return null;
        }
        Map<String, Object> attrInfos = new HashMap<String, Object>();
        for (AttrInfoTemplet templet : infoTplts) {
            AttrInfo info = new AttrInfo();
            BeanUtils.copyProperties(templet, info);
            Object value = getValueFromFieldName(bean, templet.getField());
            if (value != null) {
                info.setValue(value.toString());
            }
            //根据模板中的规则对信息项的参数进行调整
            changeInfoByRules(bean, templet.getInfoRules(), info);

            attrInfos.put(templet.getField(), info);
        }
        return attrInfos;
    }

    //获取bean中对应属性名的值
    private Object getValueFromFieldName(Object bean, String fieldName) throws IllegalAccessException {
        Field[] fields;
        fields = bean.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                fields[i].setAccessible(true);
                return fields[i].get(bean);
            }
        }
        return null;
    }

    //TODO 根据模板中的规则,结合dto的实例数据对信息项的参数进行调整
    private void changeInfoByRules(Object bean, List<InfoRule> rules, AttrInfo info) throws IllegalAccessException {
        if (rules != null)
            for (InfoRule rule : rules) {
                if (rule.getInfoAttr().equals("label")) {
                    Object labelValue = getValueFromFieldName(bean, rule.getValueOrginName());
                    if (labelValue != null) {
                        info.setLabel(labelValue.toString());
                    }
                    continue;
                }
                if (rule.getInfoAttr().equals("value")) {
                    Object txtValue = getValueFromFieldName(bean, rule.getValueOrginName());
                    if (txtValue != null) {
                        info.setValue(txtValue.toString());
                    }
                    continue;
                }
                if (rule.getInfoAttr().equals("type")) {
                    Object typeValue = getValueFromFieldName(bean, rule.getValueOrginName());
                    if (typeValue != null) {
                        if (rule.getValueFunc() != null && rule.getValueFunc().equals("IfNotEmptySelect")) {
                            List list = (List) typeValue;
                            if (!CollectionUtils.isEmpty(list)) {
                                info.setType("select");
                            } else {
                                info.setType("input");
                            }
                            continue;
                        }
                        info.setType(typeValue.toString());
                        continue;
                    }
                }
            }
    }


    // 获取该类下所有属性的信息项配置模板
    private List<AttrInfoTemplet> getAttrInfoTempletsByClassName(
            String className, Object busiQryCond) {
        List<AttrInfoTemplet> tplts = new ArrayList<AttrInfoTemplet>();
        // todo 查询数据库并组装信息项模板
        if (className
                .equals("AppProdAttrDTO")) {
            AttrInfoTemplet info = new AttrInfoTemplet();
            info.setField("appProdAttr");
            List<InfoRule> rules = new ArrayList<InfoRule>();
            InfoRule rule1 = new InfoRule();
            rule1.setInfoAttr("label");
            rule1.setValueOrginName("attrName");
            rules.add(rule1);
            InfoRule rule2 = new InfoRule();
            rule2.setInfoAttr("type");
            rule2.setValueOrginName("prodAttrValues");
            rule2.setValueFunc("IfNotEmptySelect");
            rules.add(rule2);
            info.setInfoRules(rules);
            tplts.add(info);

            AttrInfoTemplet info2 = new AttrInfoTemplet();
            info2.setField("comments");
            info2.setType("input");
            info2.setOptions("some options");
            tplts.add(info2);


        }
        if (className.equals("AppProdAttrValueDTO")) {
            AttrInfoTemplet info = new AttrInfoTemplet();
            info.setField("attrValueName");
            info.setLabel("属性");
            info.setType("input");
            info.setOptions("wowo");
            tplts.add(info);
        }

        if(className.equals("AppOrdProdInstAttrDTO")){
            AttrInfoTemplet info = new AttrInfoTemplet();
            info.setField("attrValue");
            info.setLabel("属性值");
            info.setType("input");
            tplts.add(info);
        }

        if(className.equals("AppPartyCertDTO")){
            AttrInfoTemplet info = new AttrInfoTemplet();
            info.setField("certAddr");
            info.setLabel("地球");
            info.setType("input");
            tplts.add(info);
        }

        if(className.equals("AppAccountDTO")){
            AttrInfoTemplet info = new AttrInfoTemplet();
            info.setField("totalPaymentAmount");
            info.setLabel("总额度");
            info.setType("input");
            tplts.add(info);

            AttrInfoTemplet info2 = new AttrInfoTemplet();
            info2.setField("acctLoginName");
            info2.setLabel("登录名");
            info2.setType("input");
            tplts.add(info2);
        }
        if(className.equals("Company")){
            AttrInfoTemplet info = new AttrInfoTemplet();
            info.setField("comName");
            info.setLabel("公司名");
            info.setType("input");
            tplts.add(info);
        }
        return tplts;
    }

    public static void main(String arg[]) throws IllegalAccessException, IOException, InvocationTargetException, NoSuchMethodException{

    }

}
