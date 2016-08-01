package com.zhandaAdmin.data.dao.entity;

/**
 * Created by admin on 2016/7/26.
 */
public class BusiObjAttr {
    Long Id;
    Long parentId;
    String tab;
    String col;
    Long referenceId;
    Boolean isUnique;
    BusiObj parentObj;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }


    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Boolean getUnique() {
        return isUnique;
    }

    public void setUnique(Boolean unique) {
        isUnique = unique;
    }

    public BusiObj getParentObj() {
        return parentObj;
    }

    public void setParentObj(BusiObj parentObj) {
        this.parentObj = parentObj;
    }
}
