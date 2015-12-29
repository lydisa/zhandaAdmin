package com.zhandaAdmin.service;

import java.util.List;

import com.zhandaAdmin.data.dao.entity.Company;

public interface ICompanyService {
public Integer saveCompany(Company com);
public Company getCompanyById(Integer id);
public List<Company> getCompanyList();
}
