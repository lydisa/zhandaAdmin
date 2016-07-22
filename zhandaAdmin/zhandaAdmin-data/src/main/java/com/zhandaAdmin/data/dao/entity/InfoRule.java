package com.zhandaAdmin.data.dao.entity;

/**
 * Created by admin on 2016/7/20.
 */
public class InfoRule {
    private String infoAttr;//label value function option等信息项属性
    private String valueOrginName;
    private String valueFunc;
    public String getInfoAttr() {
        return infoAttr;
    }

    public void setInfoAttr(String infoAttr) {
        this.infoAttr = infoAttr;
    }

    public String getValueOrginName() {
        return valueOrginName;
    }

    public void setValueOrginName(String valueOrginName) {
        this.valueOrginName = valueOrginName;
    }

    public String getValueFunc() {
        return valueFunc;
    }

    public void setValueFunc(String valueFunc) {
        this.valueFunc = valueFunc;
    }


}
