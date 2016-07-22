package com.zhandaAdmin.data.dao.entity;

import java.util.List;

/**
 * Created by admin on 2016/7/20.
 */
public class AttrInfoTemplet extends AttrInfo{

    private List<InfoRule> infoRules;


    public List<InfoRule> getInfoRules() {
        return infoRules;
    }

    public void setInfoRules(List<InfoRule> infoRules) {
        this.infoRules = infoRules;
    }
}
