package com.zhandaAdmin.controller;

import java.util.List;
import java.util.Map;

import com.zhandaAdmin.controller.interceptor.InfoItemAble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhandaAdmin.common.dao.CommonResult;
import com.zhandaAdmin.data.dao.entity.Company;
import com.zhandaAdmin.service.ICompanyService;

@Controller
@RequestMapping("/company")
public class CompanyContorller {
	
	@Autowired
	@Qualifier("companyService")
	private ICompanyService companyService;

	@RequestMapping(value = "/getAllCompany", method = RequestMethod.POST)
	@ResponseBody
//	@InfoItemAble(formId = "2")
	public CommonResult getAllCompany(@RequestBody  Company company) throws Exception {
		//Company company= new Company();
		//Integer id = Integer.parseInt(param.get("comId"));
		//company.setComId(id);
		List<Company> companyList = companyService.getCompanyList();
		CommonResult result = null;
		if(companyList != null)
			result = new CommonResult("0","success",companyList);
		else
			result =new CommonResult("-1","failed",null);
		return result;

	}

	@RequestMapping(value = "/getCompanyById", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult getCompanyById(@RequestBody  Company company) throws Exception {
		//Company company= new Company();
		//Integer id = Integer.parseInt(param.get("comId"));
		//company.setComId(id);
		company = companyService.getCompanyById(company.getComId());
		CommonResult result = null;
		if(company != null)
			result = new CommonResult("0","success",company);
		else
			result =new CommonResult("-1","failed",null);
		return result;

	}
}
