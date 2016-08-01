package com.zhandaAdmin.data.dao.entity;

import java.util.List;

/**
 * Created by admin on 2016/7/26.
 */
public class BusiObj {
    Long Id;
    Long parentId;
    String tab;
    String col;
    String name;
    Long referenceId;
    Boolean isUnique;
    List<BusiObj> attrs;
    BusiObj parentObj;
    BusiObj referenceObj;

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

    public List<BusiObj> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<BusiObj> attrs) {
        this.attrs = attrs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BusiObj getReferenceObj() {
        return referenceObj;
    }

    public void setReferenceObj(BusiObj referenceObj) {
        this.referenceObj = referenceObj;
    }
}
