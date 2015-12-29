package com.zhandaAdmin.common.dao;

import java.io.Serializable;

public class CommonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 0�ɹ���-1ʧ��
     */
    private String resultCode;
    /**
     * ��Ҫ�쳣��Ϣ
     */
    private String resultMsg;
    /**
     * ����ǳɹ����󣬴��������Ϣ�����ʧ�ܣ����UdalException�쳣��Ϣ
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