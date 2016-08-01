package com.zhandaAdmin.data.dao.entity;

import java.util.List;

/**
 * Created by admin on 2016/7/22.
 */
public class testEntity extends BaseEntity {
    private List<Company> companys;
    private String unionName;
    private Company mainCompany;

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public Company getMainCompany() {
        return mainCompany;
    }

    public void setMainCompany(Company mainCompany) {
        this.mainCompany = mainCompany;
    }
}
