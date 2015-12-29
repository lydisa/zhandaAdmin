package com.zhandaAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhandaAdmin.data.dao.ICompanyDao;
import com.zhandaAdmin.data.dao.entity.Company;

@Service("companyService")
public class CompanyService implements ICompanyService {

	@Autowired
	@Qualifier("companyDao")
	private ICompanyDao companyDao;

	public Integer saveCompany(Company com) {
		companyDao.saveOrUpdate(com);
		return companyDao.bulkUpdate("SELECT LAST_INSERT_ID()");
	}

	public Company getCompanyById(Integer id) {
		Company com = companyDao.get(id);
		return com;
	}

	public List<Company> getCompanyList() {
		List<Company> coms  = companyDao.loadAll();
		return coms;
	}

}
