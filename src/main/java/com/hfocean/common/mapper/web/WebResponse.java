package com.hfocean.common.mapper.web;

import com.hfocean.common.mapper.exception.BaseBusinessException;

import java.io.Serializable;

/**
 * 通用返回结果类
 *
 * @author Gene
 */
public class WebResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	//状态码
    private String code = "success";

    //返回信息
    private String message = "";

    //返回数据
    private Object data = null;

    public WebResponse() {
    }

    public WebResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public WebResponse(Object data) {
        this.data = data;
    }

    public WebResponse(BaseBusinessException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public WebResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public WebResponse returnMsg(String message) {
        this.message = message;
        return this;
    }

    public WebResponse returnAddMsg() {
        this.message = "新增成功";
        return this;
    }

    public WebResponse returnUpdateMsg() {
        this.message = "更新成功";
        return this;
    }

    public WebResponse returnDeleteMsg() {
        this.message = "删除成功";
        return this;
    }

    public WebResponse addData(Object obj) {
        this.data = obj;
        return this;
    }

}
