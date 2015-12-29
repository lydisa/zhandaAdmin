package com.zhandaAdmin.common.dao;

import java.io.Serializable;

public class CommonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 0成功，-1失败
     */
    private String resultCode;
    /**
     * 简要异常信息
     */
    private String resultMsg;
    /**
     * 如果是成功请求，存放数据信息，如果失败，存放UdalException异常信息
     */
    private Object data;

    public CommonResult() {

    }

    public CommonResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public CommonResult(String resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}