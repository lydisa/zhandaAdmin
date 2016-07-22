package com.zhandaAdmin.data.dao.entity;

import java.io.Serializable;


public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6000194605591696562L;

	/**
	 * API编码，用于标识所需要请求的服务功能的编码
	 */
	private String apiCode;

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
}
