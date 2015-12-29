package com.zhandaAdmin.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhandaAdmin.common.dao.GenericDao;
import com.zhandaAdmin.data.dao.ICompanyDao;
import com.zhandaAdmin.data.dao.entity.Company;

@Repository("companyDao")
public class CompanyDao extends GenericDao<Company,Integer> implements ICompanyDao{

}
