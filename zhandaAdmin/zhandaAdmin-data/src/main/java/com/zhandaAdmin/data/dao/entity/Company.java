package com.zhandaAdmin.data.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6389118256745238369L;
	@Id
	@Column(name = "COM_ID")
	private Integer comId;
	
	@Column(name = "COM_NAME")
	private String comName;
	
	@Column(name = "COM_ADDR")
	private String comAddr;

	@Column(name = "COM_PERSON")
	private String comPerson;

	@Column(name = "COM_PHONE")
	private String comPhone;

	@Column(name = "COM_FAX")
	private String comFax;

	@Column(name = "COM_TYPE")
	private String comType;

	public int getComId() {

		return this.comId;
	}

	public void setComId(int comId) {

		this.comId = comId;
	}

	public String getComName() {

		return this.comName;
	}

	public void setComName(String comName) {

		this.comName = comName;
	}

	public String getComAddr() {

		return this.comAddr;
	}

	public void setComAddr(String comAddr) {

		this.comAddr = comAddr;
	}

	public String getComPerson() {

		return this.comPerson;
	}

	public void setComPerson(String comPerson) {

		this.comPerson = comPerson;
	}

	public String getComPhone() {

		return this.comPhone;
	}

	public void setComPhone(String comPhone) {

		this.comPhone = comPhone;
	}

	public String getComFax() {

		return this.comFax;
	}

	public void setComFax(String comFax) {

		this.comFax = comFax;
	}

	public String getComType() {

		return this.comType;
	}

	public void setComType(String comType) {

		this.comType = comType;
	}
}
